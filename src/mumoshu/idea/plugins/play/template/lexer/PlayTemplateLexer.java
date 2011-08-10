package mumoshu.idea.plugins.play.template.lexer;

import com.intellij.lexer.DelegateLexer;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateLexer extends DelegateLexer {
    public PlayTemplateLexer() {
        super(new FlexAdapter(new PlayTemplateFlexLexer()));
    }
}
