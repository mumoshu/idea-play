/* It's an automatically generated code. Do not modify it. */
package mumoshu.idea.plugins.play;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.*;
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
%implements FlexLexer,ELHostLexer
%function advance
%type IElementType
%eof{ return;
%eof}

%state EXPRESSION
%state TAG
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

# state EXPRESSION
EXPRESSION_START="${"
EXPRESSION_BODY=[^\}]*
EXPRESSION_END="}"

# state COMMENT
COMMENT_START="*{"
COMMENT_BODY=[^\}]*
COMMENT_END="}*"

# state TAG
TAG_START="#{"
TAG_NAME={ID}
TAG_ARG={STRING_LITERAL} | {ARG_KEY} {KEY_VAL_SEP} {ARG_VAL}
EMPTY_TAG_END="#{/"

%%

<YYINITIAL> {EXPRESSION_START} { yybegin(EXPRESSION); return PlayTemplateTokenTypes.EXPRESSION_START; }
<YYINITIAL> {TAG_START} { yybegin(TAG_NAME); return PlayTemplateTokenTypes.ONELINE_TAG_START; }
<YYINITIAL> {COMMENT_START} { yybegin(COMMENT); return PlayTemplateTokenTypes.COMMENT_START; }

//<EXPRESSION> {EXPRESSION_START} { return PlayTemplateTokenTypes.EXPRESSION_START; }
<EXPRESSION> {EXPRESSION_BODY} { return PlayTemplateTokenTypes.EXPRESSION_BODY; }
<EXPRESSION> {EXPRESSION_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.EXPRESSION_END; }

<TAG_NAME> {TAG_NAME} { return PlayTemplateTokenTypes.TAG_NAME; }
<TAG_NAME> {TAG_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.ONELINE_TAG_END; }

<COMMENT> {COMMENT_START} { return PlayTemplateTokenTypes.COMMENT_START; }
<COMMENT> {COMMENT_BODY} { return PlayTemplateTokenTypes.COMMENT_BODY; }
<COMMENT> {COMMENT_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.COMMENT_END; }

[^]+ { return PlayTemplateTokenTypes.TEXT; }

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
