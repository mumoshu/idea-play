package mumoshu.idea.plugins.play.highlighter;

import com.intellij.ide.highlighter.HtmlFileHighlighter;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateTokenTypes;
import org.jetbrains.plugins.groovy.highlighter.GroovySyntaxHighlighter;

import javax.swing.text.DefaultHighlighter;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 0:45
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateEditorHighlighter extends LayeredLexerEditorHighlighter {

    public PlayTemplateEditorHighlighter(EditorColorsScheme scheme, Project project, VirtualFile virtualFile) {
        super(new PlayTemplateSyntaxHighlighter(), scheme);
        registerHtmlHighlighter();
        registerGroovyHighlighter();
    }

    private void registerGroovyHighlighter() {
        SyntaxHighlighter highlighter = new GroovySyntaxHighlighter();
        LayerDescriptor descriptor = new LayerDescriptor(highlighter, "\n");
        registerLayer(PlayTemplateTokenTypes.SCRIPT_BODY, descriptor);
    }

    private void registerHtmlHighlighter() {
        SyntaxHighlighter highlighter = new HtmlFileHighlighter();
        LayerDescriptor descriptor = new LayerDescriptor(highlighter, "\n");
        registerLayer(PlayTemplateTokenTypes.TEXT, descriptor);
    }

}
