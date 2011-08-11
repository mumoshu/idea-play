package mumoshu.idea.plugins.play.template.lang;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageUtil;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.psi.tree.TokenSet;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateLexer;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateTokenTypes;
import mumoshu.idea.plugins.play.template.parser.PlayTemplateStubFileElementType;
import mumoshu.idea.plugins.play.template.psi.CommentBodyPsiElement;
import mumoshu.idea.plugins.play.template.psi.impl.PlayTemplateFileImpl;
import org.jetbrains.annotations.NotNull;

import static com.intellij.lang.ParserDefinition.SpaceRequirements.MAY;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateParserDefinition implements ParserDefinition {
    public static final IStubFileElementType PLAY_TEMPLATE_FILE = new PlayTemplateStubFileElementType(PlayTemplateLanguage.INSTANCE);

    TokenSet WHITE_SPACE_TOKENS = TokenSet.create(PlayTemplateTokenTypes.WHITE_SPACE);
    TokenSet COMMENT_TOKENS = TokenSet.create(PlayTemplateTokenTypes.COMMENT_BODY);
    TokenSet STRING_LITERAL_ELEMENTS = TokenSet.create(PlayTemplateTokenTypes.STRING_LITERAL);

    @NotNull
    public Lexer createLexer(Project project) {
        return new PlayTemplateLexer();
    }

    public PsiParser createParser(Project project) {
        return new PlayTemplatePsiParser();
    }

    public IFileElementType getFileNodeType() {
        return PLAY_TEMPLATE_FILE;
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACE_TOKENS;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENT_TOKENS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return STRING_LITERAL_ELEMENTS;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        IElementType elem = node.getElementType();

        if (elem == PlayTemplateTokenTypes.COMMENT_BODY) {
            return new CommentBodyPsiElement(node);
        }

        return new ASTWrapperPsiElement(node);
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new PlayTemplateFileImpl(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        if (left.getElementType() == PlayTemplateTokenTypes.TAG_ARG_NAME && right.getElementType() == PlayTemplateTokenTypes.ARGUMENT_NAME_VALUE_SEPARATOR) {
            return MAY;
        }

        if (left.getElementType() == PlayTemplateTokenTypes.TAG_ARG_VALUE && right.getElementType() == PlayTemplateTokenTypes.ARGUMENT_SEPARATOR) {
            return MAY;
        }

        return LanguageUtil.canStickTokensTogetherByLexer(left, right, new PlayTemplateLexer());
    }
}