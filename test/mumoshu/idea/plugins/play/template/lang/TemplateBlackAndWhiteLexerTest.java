package mumoshu.idea.plugins.play.template.lang;

import com.intellij.lexer.HtmlLexer;
import com.intellij.lexer.Lexer;
import com.intellij.psi.templateLanguages.TemplateBlackAndWhiteLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.testFramework.LightIdeaTestCase;
import com.sun.xml.internal.ws.client.ClientSchemaValidationTube;
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

    String testData = "#{list items:hoge, as:'item'}aaaaa#{/list}\n"
                + " <a>link</a> \n"
                + "*{ comment }* \n"
                + " \t %{ script }% \t \n"
                + "#{verbatim}"
                + "  ${title} --> <h1>Title</h1>"
                + "#{/verbatim}"
                + "<h1>&{'clientName', client.name}</h1>"
                + "<a href=\"@{Clients.showAccoutns(client.id)}\">All accounts</a>\n"
                + "<a href=\"@{Clients.index()}\">Back</a>"
                + "%{\n"
                + "  for(account in client.accounts) {\n"
                + "}%\n"
                + "<li>${account}</li>\n"
                + "%{\n"
                + "}\n"
                + "}%\n";
    int numLoops = 50;

    public void testPlayLexer() {
        PlayTemplateLexer lexer = new PlayTemplateLexer();

        doTestWithLexer(lexer);
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

        doTestWithLexer(lexer);
    }

    private void doTestWithLexer(Lexer lexer) {
        lexer.start(testData, 0, testData.length());
        for (int i=0; i<numLoops; i++) {
            printAndAdvance(lexer);
            if (lexer.getTokenType() == null) {
                System.out.println("Read all!\n");
                break;
            }
        }
    }

    private void printAndAdvance(Lexer lexer) {
        printLexer(lexer);
        lexer.advance();
    }

    private void printLexer(Lexer lexer) {
        String formattedTokenType = String.format("%30s", lexer.getTokenType());
        String escapedTokenText = lexer.getTokenText()
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\t", "\\t");
        String lexerInString = String.format("%s: \"%s\"", formattedTokenType, escapedTokenText);
        System.out.println(lexerInString);
    }

}
