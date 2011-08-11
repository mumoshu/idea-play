package mumoshu.idea.plugins.play.template.parser;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IStubFileElementType;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateStubFileElementType extends IStubFileElementType {

    public PlayTemplateStubFileElementType(Language language) {
        super(language);
    }

    public PlayTemplateStubFileElementType(@org.jetbrains.annotations.NonNls String debugName, Language language) {
        super(debugName, language);
    }
}
