package mumoshu.idea.plugins.play.config;

import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryCreator;
import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryDescription;
import mumoshu.idea.plugins.play.PlayIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
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
