package mumoshu.idea.plugins.play;

import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.LibraryKind;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.LibraryEditor;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import mumoshu.idea.plugins.play.config.PlayLibraryPresentationProviderBase;
import mumoshu.idea.plugins.play.config.PlayLibraryProperties;
import mumoshu.idea.plugins.play.util.JarUtils;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public class PlayLibraryPresentationProvider extends PlayLibraryPresentationProviderBase {
  public static final LibraryKind<PlayLibraryProperties> PLAY_KIND = LibraryKind.create("play");

  public PlayLibraryPresentationProvider() {
    super(PLAY_KIND);
  }

  public boolean managesLibrary(final VirtualFile[] libraryFiles) {
    return getLibraryVersion(libraryFiles) != null;
  }

  @Nls
  public String getLibraryVersion(final VirtualFile[] libraryFiles) {
      for (VirtualFile file : libraryFiles) {
          if (file.getName().equals("play.jar")) {
              return JarUtils.getSpecificationVersion(file.getPath());
          }
      }
      return "<Library version not found>";
  }

  @NotNull
  public Icon getIcon() {
    return PlayIcons.PLAY_ICON_16x16;
  }

    public File getPlayJarFile(String playHome) {
        VirtualFile frameworkDirectory = LocalFileSystem.getInstance().findFileByPath(playHome + "/framework");
        if (frameworkDirectory == null) {
            return null;
        }

        File distDir = new File(frameworkDirectory.getPath());
        final Pattern pattern = Pattern.compile("play-(.+)\\.jar");
        File[] files = distDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        if (files.length > 0) {
            return files[0];
        } else {
            return null;
        }
    }

  @Override
  public boolean isSDKHome(@NotNull VirtualFile file) {
      // We say that the directory contains Play if `play.jar` exists inside.
      VirtualFile frameworkDirectory = file.findChild("framework");

      if (frameworkDirectory == null) {
          return false;
      }

      boolean hasFrameworkDirectory = frameworkDirectory.isDirectory();
      File distDir = new File(frameworkDirectory.getPath());
      final Pattern pattern = Pattern.compile("play-(.+)\\.jar");
      File[] files = distDir.listFiles(new FilenameFilter() {
          public boolean accept(File dir, String name) {
              return pattern.matcher(name).matches();
          }
      });

      if (files == null) {
          files = new File[0];
      }

      return files.length > 0;
  }

  @Override
  protected void fillLibrary(String path, LibraryEditor libraryEditor) {
      // Add source root
      File srcRoot = new File(path + "/framework/src");
      if (srcRoot.exists()) {
          libraryEditor.addRoot(VfsUtil.getUrlForLibraryRoot(srcRoot), OrderRootType.SOURCES);
      }

      // Add all jars under $PLAY_HOME/framework
      File[] frameworkJars = null;
      File frameworkDir = new File(path + "/framework");
      if (frameworkDir.exists()) {
          frameworkJars = frameworkDir.listFiles();
      }

      File[] libJars = null;
      File libDir = new File(path + "/framework/lib");
      if (libDir.exists()) {
          libJars = libDir.listFiles();
      }

      if (frameworkJars != null) {
          for (File file : frameworkJars) {
              if (file.getName().endsWith(".jar")) {
                  libraryEditor.addRoot(VfsUtil.getUrlForLibraryRoot(file), OrderRootType.CLASSES);
              }
          }
      }

      if (libJars != null) {
          for (File file : libJars) {
              if (file.getName().endsWith(".jar")) {
                  libraryEditor.addRoot(VfsUtil.getUrlForLibraryRoot(file), OrderRootType.CLASSES);
              }
          }
      }
  }

  @NotNull
  @Override
  public String getSDKVersion(String path) {
    return JarUtils.getSpecificationVersion(getPlayJarFile(path));
  }

  @Nls
  @NotNull
  @Override
  public String getLibraryCategoryName() {
    return "Play";
  }
}