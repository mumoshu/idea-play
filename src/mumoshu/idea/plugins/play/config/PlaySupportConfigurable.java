package mumoshu.idea.plugins.play.config;

import com.intellij.ide.util.frameworkSupport.FrameworkSupportConfigurable;
import com.intellij.ide.util.frameworkSupport.FrameworkVersion;
import com.intellij.ide.util.frameworkSupport.FrameworkVersionWithLibrary;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.libraries.Library;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 10:11
 * To change this template use File | Settings | File Templates.
 */
public class PlaySupportConfigurable extends FrameworkSupportConfigurable {
  private FrameworkVersionWithLibrary myVersion;

  public PlaySupportConfigurable() {
  }

  @Override
  public FrameworkVersion getSelectedVersion() {
    if (myVersion == null) {
      myVersion = new FrameworkVersionWithLibrary("", true, new PlayLibraryDescription());
    }
    return myVersion;
  }

  public JComponent getComponent() {
    return null;
  }

  public void addSupport(@NotNull final Module module, @NotNull final ModifiableRootModel rootModel, @Nullable Library library) {
  }
}