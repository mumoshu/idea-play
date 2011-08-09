package mumoshu.idea.plugins.play.config;

import com.intellij.ide.util.frameworkSupport.FrameworkSupportConfigurable;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 0:27
 * To change this template use File | Settings | File Templates.
 */
public class PlayFacetSupportProvider extends FrameworkSupportProvider {
    @NotNull
    @Override
    public FrameworkSupportConfigurable createConfigurable(@NotNull FrameworkSupportModel frameworkSupportModel) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEnabledForModuleType(@NotNull ModuleType moduleType) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
