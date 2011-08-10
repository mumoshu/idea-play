package mumoshu.idea.plugins.play.highlighter;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

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
//    registerPlayTemplateDocHighlighter();
  }

//  private void registerPlayTemplateDocHighlighter() {
//    // Register GroovyDoc Highlighter
//    SyntaxHighlighter groovyDocHighlighter = new GroovyDocSyntaxHighlighter();
//    final LayerDescriptor groovyDocLayer = new LayerDescriptor(groovyDocHighlighter, "\n", DefaultHighlighter.DOC_COMMENT_CONTENT);
//    registerLayer(GroovyDocElementTypes.GROOVY_DOC_COMMENT, groovyDocLayer);
//  }

}
