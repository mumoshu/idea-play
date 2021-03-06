package mumoshu.idea.plugins.play.config;

import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryCreator;
import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryDescription;
import mumoshu.idea.plugins.play.PlayIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Used by IDEA when the user is going to add framework support to specific module.
 * This class is used to add 'Play' to the list of frameworks.
 *
 * See #{link PlayLibraryDescription} to see how to construct framework's library itself.
 *
 * @author KUOKA Yusuke
 */
public class PlayLibraryCreator extends CustomLibraryCreator {
  private final PlayLibraryDescription myDescription;

  public PlayLibraryCreator() {
    myDescription = new PlayLibraryDescription();
  }

  @Override
  public String getDisplayName() {
    return "Play";
  }

  @Override
  public Icon getIcon() {
    return PlayIcons.PLAY_ICON_16x16;
  }

  @NotNull
  @Override
  public CustomLibraryDescription getDescription() {
    return myDescription;
  }
}
