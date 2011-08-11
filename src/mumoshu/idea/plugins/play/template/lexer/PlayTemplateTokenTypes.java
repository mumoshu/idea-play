package mumoshu.idea.plugins.play.template.lexer;

import com.intellij.lang.Language;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import mumoshu.idea.plugins.play.template.lang.PlayTemplateLanguage;
import mumoshu.idea.plugins.play.template.parser.outer.PlayInGroovyElementType;
import mumoshu.idea.plugins.play.template.parser.outer.PlayInHtmlElementType;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public interface PlayTemplateTokenTypes {
    IFileElementType FILE = new IFileElementType(Language.findInstance(PlayTemplateLanguage.class));

    IElementType EMBEDDED_JAVASCRIPT = null;
    IElementType EMBEDDED_CSS = null;
    IElementType EMBEDDED_GROOVY = null;

    // #{tag arg1:expr arg2:expr}
    //   body
    // #{/tag}
    //
    // expr ::= 'str' | id
    IElementType TAG_START = new PTElementType("TAG_START");
    IElementType TAG_NAME = new PTElementType("TAG_NAME");
    IElementType TAG_ARG_NAME = new PTElementType("TAG_ARG_NAME");
    IElementType ARGUMENT_NAME_VALUE_SEPARATOR = new PTElementType("ARGUMENT_NAME_VALUE_SEPARATOR");
    IElementType ARGUMENT_SEPARATOR = new PTElementType("ARGUMENT_SEPERATOR"); // comma
    IElementType TAG_ARG_VALUE = new PTElementType("TAG_ARG_VALUE");
    IElementType TAG_BODY = new PTElementType("TAG_BODY");
    IElementType TAG_END = new PTElementType("TAG_END");

    // #{tag arg1: expr /}
    // tag without body.
    IElementType ONELINE_TAG_START = new PTElementType("ONELINE_TAG_START");
    IElementType EMPTY_TAG_END = new PTElementType("ONELINE_TAG_END");

    /**
     * '#{/'
     */
    IElementType CLOSE_TAG_START = new PTElementType("CLOSE_TAG_START");

    //
    IElementType EXPRESSION_START = new PTElementType("EXPRESSION_START");
    IElementType EXPRESSION_BODY = new PTElementType("EXPRESSION_BODY");
    IElementType EXPRESSION_END = new PTElementType("EXPRESSION_END");

    // Messages: &{'foo.bar', foo.bar}
    IElementType MESSAGE_START = new PTElementType("MESSAGE_START");

    IElementType MESSAGE_KEY = new PTElementType("MESSAGE_KEY");
    IElementType MESSAGE_ARG = new PTElementType("MESSAGE_ARG");
    IElementType MESSAGE_END = new PTElementType("MESSAGE_END");
    
    // Comment: *{...}*
    IElementType COMMENT_START = new PTElementType("COMMENT_START");
    IElementType COMMENT_BODY = new PTElementType("COMMENT_BODY");
    IElementType COMMENT_END = new PTElementType("COMMENT_END");

    // Scripts: %{...}%
    IElementType SCRIPT_START = new PTElementType("SCRIPT_START");
    IElementType SCRIPT_BODY = new PTElementType("SCRIPT_BODY"); // Groovy code
    IElementType SCRIPT_END = new PTElementType("SCRIPT_END");

    IElementType TEXT = new PTElementType("TEXT");

    IElementType ID = new PTElementType("ID"); // foo.bar

    IElementType WHITE_SPACE = new PTElementType("WHITE_SCAPE");
    IElementType COMMA = new PTElementType("COMMA"); // .
    IElementType SINGLE_QUOTE = new PTElementType("SINGLE_QUOTE"); // '
    IElementType STRING_LITERAL = new PTElementType("STRING_LITERAL"); // 'foo'

    /**
     *  PlayTemplateLexer should creates at least three important tokens:
     *  <ul>
     *      <li>PLAY</li>
     *      <li>HTML</li>
     *      <li>GROOVY</li>
     *  </ul>
     *  HTML and GROOVY tokens are passed to
     */
    IElementType HTML_FRAGMENT_IN_PLAY = new PTElementType("HTML_FRAGMENT_IN_PLAY");
    IElementType PLAY_FRAGMENT_IN_HTML = new PlayInHtmlElementType();

    /**
     * Root of HTML tree. HTML tree covers *all* the text in file.
     * Groovy and Play template code in the file are expressed as a PLAY_FRAGMENT_IN_HTML,
     * and ignored in the HTML tree.
     *
     * See:
     * http://devnet.jetbrains.com/thread/294172;jsessionid=0161DA9E6DEFD1658F4EA0BAC20D353C?decorator=print&displayFullThread=true
     */
    IElementType HTML = new TemplateDataElementType(
            "HTML_IN_PLAY_TEMPLATE", PlayTemplateLanguage.INSTANCE, HTML_FRAGMENT_IN_PLAY, PLAY_FRAGMENT_IN_HTML);

    IElementType GROOVY_FRAGMENT_IN_PLAY = new PTElementType("GROOVY_FRAGMENT_IN_PLAY");
    IElementType PLAY_FRAGMENT_IN_GROOVY = new PlayInGroovyElementType();
    /**
     * Root of Groovy tree.
     * Groovy tree covers *all* the text in file.
     * HTML and Play template code in the file are expressed as a PLAY_FRAGMENT_IN_GROOVY,
     * and ignored in the Groovy tree.
     *
     * See:
     * http://devnet.jetbrains.com/thread/294172;jsessionid=0161DA9E6DEFD1658F4EA0BAC20D353C?decorator=print&displayFullThread=true
     */
    IElementType GROOVY = new TemplateDataElementType(
            "GROOVY_IN_PLAY_TEMPLATE", PlayTemplateLanguage.INSTANCE, GROOVY_FRAGMENT_IN_PLAY, PLAY_FRAGMENT_IN_GROOVY);

}
