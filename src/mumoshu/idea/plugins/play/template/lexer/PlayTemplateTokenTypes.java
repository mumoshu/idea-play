package mumoshu.idea.plugins.play.template.lexer;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import mumoshu.idea.plugins.play.PlayTemplateLanguage;

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
    IElementType TAG_ARG_VALUE = new PTElementType("TAG_ARG_VALUE");
    IElementType TAG_BODY = new PTElementType("TAG_BODY");
    IElementType TAG_END = new PTElementType("TAG_END");

    // #{tag arg1: expr /}
    // tag without body.
    IElementType ONELINE_TAG_START = new PTElementType("ONELINE_TAG_END");
    IElementType ONELINE_TAG_END = new PTElementType("ONELINE_TAG_END");

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

    // '
    IElementType SINGLE_QUOTE = new PTElementType("SINGLE_QUOTE");
    // ,
    IElementType COMMA = new PTElementType("COMMA");

    IElementType TEXT = new PTElementType("TEXT");
}
