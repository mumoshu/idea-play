package mumoshu.idea.plugins.play.config;

import com.intellij.openapi.roots.libraries.LibraryKind;
import com.intellij.openapi.roots.libraries.LibraryPresentationProvider;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.LibraryEditor;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlayLibraryPresentationProviderBase extends LibraryPresentationProvider<PlayLibraryProperties> {
  public PlayLibraryPresentationProviderBase(LibraryKind<PlayLibraryProperties> kind) {
    super(kind);
  }

  @Override
  public String getDescription(@NotNull PlayLibraryProperties properties) {
    final String version = properties.getVersion();
    return getLibraryCategoryName() + " library" + (version != null ? " of version " + version : ":");
  }

  @Override
  public PlayLibraryProperties detect(@NotNull List<VirtualFile> classesRoots) {
    final VirtualFile[] libraryFiles = VfsUtil.toVirtualFileArray(classesRoots);
    if (managesLibrary(libraryFiles)) {
      final String version = getLibraryVersion(libraryFiles);
      return new PlayLibraryProperties(version);
    }
    return null;
  }

  protected abstract void fillLibrary(String path, LibraryEditor libraryEditor);

  public abstract boolean managesLibrary(final VirtualFile[] libraryFiles);

  @Nullable
  @Nls
  public abstract String getLibraryVersion(final VirtualFile[] libraryFiles);

  @NotNull
  public abstract Icon getIcon();

  public abstract boolean isSDKHome(@NotNull VirtualFile file);

  public abstract @NotNull String getSDKVersion(String path);

  @NotNull @Nls public abstract String getLibraryCategoryName();

  @NotNull
  @Nls
  public String getLibraryPrefix() {
    return StringUtil.toLowerCase(getLibraryCategoryName());
  }

  public boolean managesName(@NotNull String name) {
    return StringUtil.startsWithIgnoreCase(name, getLibraryPrefix());
  }
}
