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
%state MESSAGE_ARG
%state COMMENT
%state SCRIPT
%state TEXT

/* IMPORTANT! number of states should not exceed 16. See JspHighlightingLexer. */

ALPHA=[:letter:]
DIGIT=[0-9]
SPACE=" "
WHITE_SPACE_CHARS=[ \t\f]+
NEW_LINE=\n

ID={ALPHA} ({ALPHA}|{DIGIT}|"_"|".")*
KEY_VAL_SEP=":"

//EXPRESSION="${" [^\}\n\r]* "}"
COMMENT="*{" [^\}\n\r]* "}*"
ONELINE_TAG="#{" [^/\n\r]* "/}"
STRING_LITERAL="'" [^']* "'"

EXPRESSION_START="${"
EXPRESSION_BODY=[^\}]*
EXPRESSION_END="}"

MESSAGE_START="&{"
MESSAGE_BODY=[^\}]*
MESSAGE_END="}*"

MESSAGE_KEY={STRING_LITERAL}
MESSAGE_ARG={EXPR}

COMMENT_START="*{"
COMMENT_BODY=[^}]*
COMMENT_END="}*"

SCRIPT_START="%{"
SCRIPT_BODY=([^}] | "}" [^%])+
SCRIPT_END="}%"

TAG_START="#{"
CLOSE_TAG_START="#{/"
TAG_NAME={ID}
EXPR={ID} | {STRING_LITERAL}
TAG_END="}"
EMPTY_TAG_END="/}"

CONTROL_CHAR=[*/%#]
NON_CONTROL_CHAR=[^*/%#]
TEXT_CHUNK=([^*/%#$] | [*/%#$] [^\{])+

%%

<YYINITIAL> {EXPRESSION_START} { yybegin(EXPRESSION); return PlayTemplateTokenTypes.EXPRESSION_START; }
<YYINITIAL> {TAG_START} { yybegin(TAG_NAME); return PlayTemplateTokenTypes.TAG_START; }
<YYINITIAL> {COMMENT_START} { yybegin(COMMENT); return PlayTemplateTokenTypes.COMMENT_START; }
<YYINITIAL> {MESSAGE_START} { yybegin(MESSAGE); return PlayTemplateTokenTypes.MESSAGE_START; }
<YYINITIAL> {COMMENT_START} { yybegin(COMMENT); return PlayTemplateTokenTypes.COMMENT_START; }
<YYINITIAL> {SCRIPT_START} { yybegin(SCRIPT); return PlayTemplateTokenTypes.SCRIPT_START; }

<YYINITIAL> {CLOSE_TAG_START} { yybegin(CLOSE_TAG); return PlayTemplateTokenTypes.CLOSE_TAG_START; }

//<EXPRESSION> {EXPRESSION_START} { return PlayTemplateTokenTypes.EXPRESSION_START; }
<EXPRESSION> {EXPRESSION_BODY} { return PlayTemplateTokenTypes.EXPRESSION_BODY; }
<EXPRESSION> {EXPRESSION_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.EXPRESSION_END; }

<TAG_NAME> {TAG_NAME} { yybegin(TAG_ARGS); return PlayTemplateTokenTypes.TAG_NAME; }
<TAG_ARGS> {WHITE_SPACE_CHARS} { return PlayTemplateTokenTypes.WHITE_SPACE; }
<TAG_ARGS> {ID} { return PlayTemplateTokenTypes.ID; }
<TAG_ARGS> ":" { return PlayTemplateTokenTypes.ARGUMENT_NAME_VALUE_SEPARATOR; }
<TAG_ARGS> {STRING_LITERAL} { return PlayTemplateTokenTypes.STRING_LITERAL; }
<TAG_ARGS> "," { return PlayTemplateTokenTypes.COMMA; }
<TAG_ARGS> {EMPTY_TAG_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.EMPTY_TAG_END; }
<TAG_ARGS> {NEW_LINE} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.NEW_LINE; }
<TAG_ARGS> {TAG_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.TAG_END; }

<CLOSE_TAG> {TAG_NAME} { return PlayTemplateTokenTypes.TAG_NAME; }
<CLOSE_TAG> {TAG_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.TAG_END; }

<MESSAGE> {MESSAGE_KEY} { yybegin(MESSAGE_ARG); return PlayTemplateTokenTypes.MESSAGE_KEY; }
<MESSAGE_ARG> {MESSAGE_ARG} { return PlayTemplateTokenTypes.MESSAGE_ARG; }
<MESSAGE_ARG> {SPACE} { return PlayTemplateTokenTypes.WHITE_SPACE; }
<MESSAGE_ARG> {MESSAGE_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.MESSAGE_END; }

<COMMENT> {COMMENT_BODY} { return PlayTemplateTokenTypes.COMMENT_BODY; }
<COMMENT> {COMMENT_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.COMMENT_END; }

<SCRIPT> {SCRIPT_BODY} { return PlayTemplateTokenTypes.SCRIPT_BODY; }
<SCRIPT> {SCRIPT_END} { yybegin(YYINITIAL); return PlayTemplateTokenTypes.SCRIPT_END; }


<YYINITIAL> {WHITE_SPACE_CHARS} { return PlayTemplateTokenTypes.WHITE_SPACE; }
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
