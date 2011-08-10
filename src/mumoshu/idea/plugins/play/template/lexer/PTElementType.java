package mumoshu.idea.plugins.play.template.lexer;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import mumoshu.idea.plugins.play.PlayTemplateFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class PTElementType extends IElementType {
    public PTElementType(@NotNull @NonNls String debugName) {
        super(debugName, PlayTemplateFileType.PLAY_TEMPLATE_FILE_TYPE.getLanguage());
    }
}
