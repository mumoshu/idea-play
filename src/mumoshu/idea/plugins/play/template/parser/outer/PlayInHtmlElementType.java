package mumoshu.idea.plugins.play.template.parser.outer;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.ILeafElementType;
import mumoshu.idea.plugins.play.template.lang.PlayTemplateLanguage;
import mumoshu.idea.plugins.play.template.psi.outer.PlayInHtmlPsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
public class PlayInHtmlElementType extends IElementType implements ILeafElementType {

    public PlayInHtmlElementType() {
        super("PLAY_FRAGMENT_IN_HTML", PlayTemplateLanguage.INSTANCE);
    }

    @NotNull
    public ASTNode createLeafNode(CharSequence leafText) {
        PlayInHtmlPsiElement outerLanguagePsiElement = new PlayInHtmlPsiElement(this, leafText);
        return outerLanguagePsiElement;
    }
}
