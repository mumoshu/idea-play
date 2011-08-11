package mumoshu.idea.plugins.play.template.psi.outer;

import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.templateLanguages.OuterLanguageElement;
import com.intellij.psi.tree.IElementType;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
public class GroovyInHtmlPsiElement extends LeafPsiElement implements OuterLanguageElement /*PsiLanguageInjectionHost*/
{
    public GroovyInHtmlPsiElement(IElementType type, CharSequence text) {
        super(type, text);
    }
}
