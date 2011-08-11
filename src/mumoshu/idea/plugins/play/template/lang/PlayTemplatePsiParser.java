package mumoshu.idea.plugins.play.template.lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 10:11
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplatePsiParser implements PsiParser {
    @NotNull
    @Override
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        // TODO parse
        return builder.getTreeBuilt();
    }
}
