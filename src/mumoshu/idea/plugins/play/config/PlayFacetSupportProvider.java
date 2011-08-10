package mumoshu.idea.plugins.play.config;

import com.intellij.ide.util.frameworkSupport.FrameworkSupportConfigurable;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleType;
import mumoshu.idea.plugins.play.PlayIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 0:27
 * To change this template use File | Settings | File Templates.
 */
public class PlayFacetSupportProvider extends FrameworkSupportProvider {
  protected PlayFacetSupportProvider() {
    super("PlayFrameworkId", "Play title");
  }

  @Override
  public Icon getIcon() {
    return PlayIcons.PLAY_ICON_16x16;
  }

  @Override
  public boolean isEnabledForModuleBuilder(@NotNull ModuleBuilder builder) {
    return super.isEnabledForModuleBuilder(builder) && !(builder instanceof PlayAwareModuleBuilder);
  }

  @NotNull
  public FrameworkSupportConfigurable createConfigurable(final @NotNull FrameworkSupportModel model) {
    return new PlaySupportConfigurable();
  }

  public boolean isEnabledForModuleType(@NotNull ModuleType moduleType) {
    return moduleType instanceof JavaModuleType;
  }
}
