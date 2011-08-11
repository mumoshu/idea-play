package mumoshu.idea.plugins.play.template.parser.outer;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.ILeafElementType;
import mumoshu.idea.plugins.play.template.lang.PlayTemplateLanguage;
import mumoshu.idea.plugins.play.template.psi.outer.PlayInGroovyPsiElement;
import mumoshu.idea.plugins.play.template.psi.outer.PlayInHtmlPsiElement;
import org.jetbrains.annotations.NotNull;

public class PlayInGroovyElementType extends IElementType implements ILeafElementType {

    public PlayInGroovyElementType() {
        super("PLAY_FRAGMENT_IN_GROOVY", PlayTemplateLanguage.INSTANCE);
    }

    @NotNull
    public ASTNode createLeafNode(CharSequence leafText) {
        PlayInGroovyPsiElement outerLanguagePsiElement = new PlayInGroovyPsiElement(this, leafText);
        return outerLanguagePsiElement;
    }
}
