package mumoshu.idea.plugins.play.template.psi.outer;

import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.templateLanguages.OuterLanguageElement;
import com.intellij.psi.tree.IElementType;

/**
 * PsiElement takes place in Groovy tree to represent Play template code.
 * Play template code should be ignored in Groovy tree.
 * This class extends {@link com.intellij.psi.templateLanguages.OuterLanguageElement} for that.
 *
 * {@link org.jetbrains.plugins.grails.lang.gsp.psi.groovy.api.GspOuterHtmlElement}
 * {@link org.jetbrains.plugins.grails.lang.gsp.psi.groovy.impl.GspOuterHtmlElementImpl}
 */
public class PlayInGroovyPsiElement extends LeafPsiElement implements OuterLanguageElement /*PsiLanguageInjectionHost*/
{
    public PlayInGroovyPsiElement(IElementType type, CharSequence text) {
        super(type, text);
    }
}

