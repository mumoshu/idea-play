/* It's an automatically generated code. Do not modify it. */
package mumoshu.idea.plugins.play.template.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.*;
import com.intellij.lexer.FlexLexer;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateTokenTypes;

%%

%unicode

%{

  public PlayTemplateFlexLexer() {
    this((java.io.Reader)null);
  }
%}

%class PlayTemplateFlexLexer
%public
%implements FlexLexer
%function advance
%type IElementType
%eof{ return;
%eof}

%state EXPRESSION
%state TAG
%state TAG_NAME
%state TAG_ARGS
%state TAG_ARG_VALUE
%state NEXT_ARG
%state CLOSE_TAG
%state ACTION
%state MESSAGE
%state COMMENT
%state SCRIPT
%state TEXT

/* IMPORTANT! number of states should not exceed 16. See JspHighlightingLexer. */

ALPHA=[:letter:]
DIGIT=[0-9]
WHITE_SPACE_CHARS=[ \n\r\t\f]+

ID={ALPHA} ({ALPHA}|{DIGIT}|"_"|".")*
KEY_VAL_SEP=":"

//EXPRESSION="${" [^\}\n\r]* "}"
COMMENT="*{" [^\}\n\r]* "}*"
ONELINE_TAG="#{" [^/\n\r]* "/}"
STRING_LITERAL="'" [^']* "'"

EXPRESSION_START="${"
EXPRESSION_BODY=[^\}]*
EXPRESSION_END="}"

COMMENT_START="*{"
COMMENT_BODY=[^\}]*
COMMENT_END="}*"

TAG_START="#{"
CLOSE_TAG_START="#{/"
TAG_NAME={ID}
EXPR={ID} | {STRING_LITERAL}
TAG_END="}"
EMPTY_TAG_END="/}"

NON_CONTROL_CHARS=[^*/%#]+
TEXT_CHUNK={NON_CONTROL_CHARS} | [*/%#] [^\{] {NON_CONTROL_CHARS}

%%

<YYINITIAL> {EXPRESSION_START} { yybegin(EXPRESSION); return PlayTemplateTokenTypes.EXPRESSION_START; }
<YYINITIAL> {TAG_START} { yybegin(TAG_NAME); return PlayTemplateTokenTypes.TAG_START; }
<YYINITIAL> {COMMENT_START} { yybegin(COMMENT); return PlayTemplateTokenTypes.COMMENT_START; }
<YYINITIAL> {CLOSE_TAG_START} { yybegin(CLOSE_TAG); return PlayTemplateTokenTypes.CLOSE_TAG_START; }

//<EXPRESSION> {EXPRESSION_START} { return PlayTemplateTokenTypes.EXPRESSION_START; }
<EXPRESSION> {EXPRESSION_BODY} { return PlayTemplateTokenTypes.EXPRESSION_BODY; }
<EXPRESSION> {EXPRESSION_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.EXPRESSION_END; }

<TAG_NAME> {TAG_NAME} { yybegin(TAG_ARGS); return PlayTemplateTokenTypes.TAG_NAME; }
<TAG_ARGS, TAG_ARG_VALUE, NEXT_ARG> {WHITE_SPACE_CHARS} { return PlayTemplateTokenTypes.WHITE_SPACE; }
<TAG_ARGS> {ID} { return PlayTemplateTokenTypes.TAG_ARG_NAME; }
<TAG_ARGS> ":" { yybegin(TAG_ARG_VALUE); return PlayTemplateTokenTypes.ARGUMENT_NAME_VALUE_SEPARATOR; }
<TAG_ARG_VALUE> {WHITE_SPACE_CHARS} { return PlayTemplateTokenTypes.WHITE_SPACE; }
<TAG_ARG_VALUE> {STRING_LITERAL} { yybegin(NEXT_ARG); return PlayTemplateTokenTypes.STRING_LITERAL; }
<TAG_ARG_VALUE> {ID} { yybegin(NEXT_ARG); return PlayTemplateTokenTypes.ID; }
<NEXT_ARG> "," { yybegin(TAG_ARGS); return PlayTemplateTokenTypes.ARGUMENT_SEPARATOR; }
<TAG_ARGS, NEXT_ARG> {EMPTY_TAG_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.EMPTY_TAG_END; }
<TAG_ARGS, NEXT_ARG> {TAG_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.TAG_END; }

<CLOSE_TAG> {TAG_NAME} { return PlayTemplateTokenTypes.TAG_NAME; }
<CLOSE_TAG> {TAG_END} { return PlayTemplateTokenTypes.TAG_END; }

<COMMENT> {COMMENT_START} { return PlayTemplateTokenTypes.COMMENT_START; }
<COMMENT> {COMMENT_BODY} { return PlayTemplateTokenTypes.COMMENT_BODY; }
<COMMENT> {COMMENT_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.COMMENT_END; }

<YYINITIAL> {TEXT_CHUNK} { return PlayTemplateTokenTypes.TEXT; }

//"&lt;" |
//"&gt;" |
//"&apos;" |
//"&quot;" |
//"&nbsp;" |
//"&amp;" |
//"&#"{DIGIT}+";" |
//"&#x"({DIGIT}|[a-fA-F])+";" { return XmlTokenType.XML_CHAR_ENTITY_REF; }
//"&"{TAG_NAME}";" { return XmlTokenType.XML_ENTITY_REF_TOKEN; }

//<YYINITIAL> ([^<&\$# \n\r\t\f]|(\\\$)|(\\#))* { return XmlTokenType.XML_DATA_CHARACTERS; }
//<YYINITIAL> [^] { return XmlTokenType.XML_DATA_CHARACTERS; }
//[^] { return PlayTemplateTokenTypes.BAD_CHARACTER; }
