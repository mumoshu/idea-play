package mumoshu.idea.plugins.play.config;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.roots.libraries.LibraryKind;
import com.intellij.openapi.roots.libraries.LibraryPresentationProvider;
import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryDescription;
import com.intellij.openapi.roots.ui.configuration.libraries.LibraryPresentationManager;
import com.intellij.openapi.roots.ui.configuration.libraries.NewLibraryConfiguration;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.LibraryEditor;
import com.intellij.openapi.roots.ui.configuration.projectRoot.LibrariesContainer;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
public class PlayLibraryDescription extends CustomLibraryDescription {
  private static final String PLAY_FRAMEWORK_NAME = "Play";
  private final Condition<List<VirtualFile>> myCondition;
  private String myEnvVariable;
  private final String myFrameworkName;

  public PlayLibraryDescription() {
    this("PLAY_HOME", getAllPlayKinds(), PLAY_FRAMEWORK_NAME);
  }

  private static Set<? extends LibraryKind<?>> getAllPlayKinds() {
    final HashSet<LibraryKind<?>> kinds = new HashSet<LibraryKind<?>>();
    for (LibraryPresentationProvider provider : LibraryPresentationProvider.EP_NAME.getExtensions()) {
      if (provider instanceof PlayLibraryPresentationProviderBase) {
        kinds.add(provider.getKind());
      }
    }
    return kinds;
  }

  public PlayLibraryDescription(@NotNull String envVariable, @NotNull LibraryKind<?> libraryKind, String frameworkName) {
    this(envVariable, Collections.singleton(libraryKind), frameworkName);
  }

  private PlayLibraryDescription(@NotNull String envVariable, @NotNull final Set<? extends LibraryKind<?>> libraryKinds, String frameworkName) {
    myEnvVariable = envVariable;
    myCondition = new Condition<List<VirtualFile>>() {
      @Override
      public boolean value(List<VirtualFile> virtualFiles) {
        return LibraryPresentationManager.getInstance().isLibraryOfKind(virtualFiles, libraryKinds);
      }
    };
    myFrameworkName = frameworkName;
  }

  @Nullable
  public static PlayLibraryPresentationProviderBase findManager(@NotNull VirtualFile dir) {
    final String name = dir.getName();

    final List<PlayLibraryPresentationProviderBase> providers = ContainerUtil.findAll(LibraryPresentationProvider.EP_NAME.getExtensions(), PlayLibraryPresentationProviderBase.class);
    for (final PlayLibraryPresentationProviderBase provider : providers) {
      if (provider.managesName(name) && provider.isSDKHome(dir)) {
        return provider;
      }
    }

    for (final PlayLibraryPresentationProviderBase manager : providers) {
      if (manager.isSDKHome(dir)) {
        return manager;
      }
    }
    return null;
  }

  @NotNull
  @Override
  public Condition<List<VirtualFile>> getSuitableLibraryCondition() {
    return myCondition;
  }

  @Override
  public NewLibraryConfiguration createNewLibrary(@NotNull JComponent parentComponent, VirtualFile contextDirectory) {
    VirtualFile initial = findFile(System.getenv(myEnvVariable));
    if (initial == null && PLAY_FRAMEWORK_NAME.equals(myFrameworkName) && SystemInfo.isLinux) {
      initial = findFile("~/");
    }

    final FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, false, false, false, false) {
      @Override
      public boolean isFileSelectable(VirtualFile file) {
        if (!super.isFileSelectable(file)) {
          return false;
        }
        return findManager(file) != null;
      }
    };
    descriptor.setTitle(myFrameworkName + " SDK");
    descriptor.setDescription("Choose a directory containing " + myFrameworkName + " distribution");
    final VirtualFile[] files = FileChooser.chooseFiles(parentComponent, descriptor, initial);
    if (files.length != 1) return null;

    final VirtualFile dir = files[0];
    final PlayLibraryPresentationProviderBase provider = findManager(dir);
    if (provider == null) {
      return null;
    }

    final String path = dir.getPath();
    final String sdkVersion = provider.getSDKVersion(path);
//    if (AbstractConfigUtils.UNDEFINED_VERSION.equals(sdkVersion)) {
//      return null;
//    }

    return new NewLibraryConfiguration(provider.getLibraryPrefix() + "-" + sdkVersion) {
      @Override
      public void addRoots(@NotNull LibraryEditor editor) {
        provider.fillLibrary(path, editor);
      }
    };
  }

  @Nullable
  private VirtualFile findFile(String path) {
    if (path != null && path.length() > 0) {
      return LocalFileSystem.getInstance().findFileByPath(FileUtil.toSystemIndependentName(path));
    }
    return null;
  }

  @NotNull
  @Override
  public LibrariesContainer.LibraryLevel getDefaultLevel() {
    return LibrariesContainer.LibraryLevel.GLOBAL;
  }
}
