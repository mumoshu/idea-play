package mumoshu.idea.plugins.play.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateLexer;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Lexer scans tokens from text. Then, Syntax Highlighter assigns font colors to each group of tokens.
 */
public class PlayTemplateSyntaxHighlighter extends SyntaxHighlighterBase implements PlayTemplateTokenTypes {

    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    static final TokenSet COMMENTS = TokenSet.create(
            COMMENT_BODY
    );

    static final TokenSet TAG_NAMES = TokenSet.create(
            TAG_NAME
    );

    public static String COMMENT_ID = "Comment";
    public static String TAG_NAME_ID = "Tag name";

    public static TextAttributesKey TEXT_ATTRIBUTE_KEY_COMMENT = TextAttributesKey.createTextAttributesKey(COMMENT_ID,
            SyntaxHighlighterColors.JAVA_BLOCK_COMMENT.getDefaultAttributes());
    public static TextAttributesKey TEXT_ATTRIBUTE_KEY_TAG_NAME = TextAttributesKey.createTextAttributesKey(TAG_NAME_ID,
            SyntaxHighlighterColors.KEYWORD.getDefaultAttributes());

  static {
    fillMap(ATTRIBUTES, COMMENTS, TEXT_ATTRIBUTE_KEY_COMMENT);
    fillMap(ATTRIBUTES, TAG_NAMES, TEXT_ATTRIBUTE_KEY_TAG_NAME);
  }

  @NotNull
  public Lexer getHighlightingLexer() {
    return new PlayTemplateLexer();
  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(ATTRIBUTES.get(tokenType));
  }
}
