package mumoshu.idea.plugins.play.config;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public class PlayAwareModuleBuilder extends JavaModuleBuilder {
  private final String myBuilderId;
  private final String myPresentableName;
  private final String myDescription;
  private final Icon myBigIcon;

  protected PlayAwareModuleBuilder(String builderId, String presentableName, String description, Icon bigIcon) {
    myBuilderId = builderId;
    myPresentableName = presentableName;
    myDescription = description;
    myBigIcon = bigIcon;
  }

  @Override
  public String getBuilderId() {
    return myBuilderId;
  }

  @Override
  public Icon getBigIcon() {
    return myBigIcon;
  }

  @Override
  public String getDescription() {
    return myDescription;
  }

  @Override
  public String getPresentableName() {
    return myPresentableName;
  }
}