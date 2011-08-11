package mumoshu.idea.plugins.play.template.psi.outer;

import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.templateLanguages.OuterLanguageElement;
import com.intellij.psi.tree.IElementType;

/**
 * PsiElement takes place in HTML tree to represent Play template code.
 * Play template code should be ignored or recognized as just text in HTML tree.
 * This class extends {@link OuterLanguageElement} for that.
 * 
 * {@link org.jetbrains.plugins.grails.lang.gsp.psi.groovy.api.GspOuterHtmlElement}
 * {@link org.jetbrains.plugins.grails.lang.gsp.psi.groovy.impl.GspOuterHtmlElementImpl}
 */
public class PlayInHtmlPsiElement extends LeafPsiElement implements OuterLanguageElement /*PsiLanguageInjectionHost*/
{
    public PlayInHtmlPsiElement(IElementType type, CharSequence text) {
        super(type, text);
    }
}
