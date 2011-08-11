package mumoshu.idea.plugins.play.template.lang;

import com.intellij.lexer.HtmlLexer;
import com.intellij.lexer.Lexer;
import com.intellij.psi.templateLanguages.TemplateBlackAndWhiteLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.testFramework.LightIdeaTestCase;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateLexer;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateTokenTypes;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class TemplateBlackAndWhiteLexerTest extends LightIdeaTestCase {

    public void testPlayLexer() {
        PlayTemplateLexer lexer = new PlayTemplateLexer();
        String text = "#{list items:hoge}aaaaa#{/list}\n"
                + "<a>link</a>";

        lexer.start(text, 0, text.length());
        for (int i=0; i<15; i++) {
            printAndAdvance(lexer);
        }
    }

    public void testBlackAndWhiteLexer() {

        PlayTemplateLexer playLexer = new PlayTemplateLexer();
        HtmlLexer htmlLexer = new HtmlLexer();
        IElementType playDataType = PlayTemplateTokenTypes.TEXT;
        IElementType htmlDataType = PlayTemplateTokenTypes.PLAY_FRAGMENT_IN_HTML;
        /**
         * See:
         * {@link com.intellij.psi.templateLanguages.TemplateDataElementType.parseContents()}
         * for an usage example of TemplateBlackAndWhiteLexer
         */
        TemplateBlackAndWhiteLexer lexer = new TemplateBlackAndWhiteLexer(playLexer, htmlLexer, playDataType, htmlDataType);

        String text = "#{list items:hoge}aaaaa#{/list}\n"
                + "<a>link</a>";

        lexer.start(text, 0, text.length());
        for (int i=0; i<15; i++) {
            printAndAdvance(lexer);
        }
    }

    private void printAndAdvance(Lexer lexer) {
        printLexer(lexer);
        lexer.advance();
    }

    private void printLexer(Lexer lexer) {
        System.out.println("      TokenType: " + lexer.getTokenType());
        System.out.println("      TokenText: " + lexer.getTokenText());
        System.out.println("CurrentPosition: " + lexer.getCurrentPosition());
        System.out.println();
    }

}
