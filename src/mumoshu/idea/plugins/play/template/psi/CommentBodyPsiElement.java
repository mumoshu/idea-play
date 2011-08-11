package mumoshu.idea.plugins.play.template.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.extapi.psi.PsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class CommentBodyPsiElement extends ASTWrapperPsiElement implements PsiElement {
    public CommentBodyPsiElement(ASTNode node) {
        super(node);
    }
}
