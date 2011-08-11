package mumoshu.idea.plugins.play.highlighter;

import com.intellij.lang.Language;
import com.intellij.lang.StdLanguages;
import com.intellij.openapi.editor.JspHighlighterColors;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateTokenTypes;
import mumoshu.idea.plugins.play.template.psi.impl.PlayTemplateFileImpl;
import org.jetbrains.plugins.groovy.highlighter.GroovySyntaxHighlighter;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 0:45
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateEditorHighlighter extends LayeredLexerEditorHighlighter {

    private Language myTemplateLanguage = null;

    private final Project myProject;
    private final VirtualFile myFile;

    public PlayTemplateEditorHighlighter(EditorColorsScheme scheme, Project project, VirtualFile virtualFile) {
        super(new PlayTemplateSyntaxHighlighter(), scheme);

        myProject = project;
        myFile = virtualFile;

        // HTML layer
//        SyntaxHighlighter highlighter = new HtmlFileHighlighter();
        SyntaxHighlighter highlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(StdLanguages.HTML, project, virtualFile);
        LayerDescriptor descriptor = new LayerDescriptor(highlighter, "\n");
        registerLayer(PlayTemplateTokenTypes.TEXT, descriptor);

        // Groovy layer
        registerGroovyHighlighter();
    }

    private void registerGroovyHighlighter() {
        SyntaxHighlighter highlighter = new GroovySyntaxHighlighter();
        LayerDescriptor descriptor = new LayerDescriptor(highlighter, "\n", JspHighlighterColors.JSP_SCRIPTING_BACKGROUND);
        registerLayer(PlayTemplateTokenTypes.SCRIPT_BODY, descriptor);
    }

    @Override
    protected boolean updateLayers() {
        Language templateLanguage = getCurrentTemplateLanguage();
        if (!Comparing.equal(myTemplateLanguage, templateLanguage)) {
//            unregisterLayer(PlayTemplateTokenTypes.TEXT);
//            myTemplateLanguage = templateLanguage;
//            com.intellij.openapi.fileTypes.SyntaxHighlighter templateLanguageHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(myTemplateLanguage, myProject, myFile);
//            registerLayer(PlayTemplateTokenTypes.TEXT, new LayerDescriptor(templateLanguageHighlighter, "", null));
            return true;
        } else {
            return false;
        }
    }

    public static PlayTemplateFileImpl getPlayTemplateFile(PsiElement element) {
        if (element == null)
            return null;
        PsiFile containingFile = element.getContainingFile();
        if (containingFile == null) {
            return null;
        } else {
            FileViewProvider viewProvider = containingFile.getViewProvider();
            PsiFile psiFile = viewProvider.getPsi(viewProvider.getBaseLanguage());
            return (psiFile instanceof PlayTemplateFileImpl) ? (PlayTemplateFileImpl)psiFile : null;
        }
    }

    private boolean isProjectPresent() {
        return myProject != null && !myProject.isDisposed() && getDocument() != null;
    }

    PlayTemplateFileImpl getCurrentPlayTemplateFile() {
        PsiDocumentManager instance = PsiDocumentManager.getInstance(myProject);
        PsiFile psiFile = instance.getPsiFile(getDocument());
        PlayTemplateFileImpl playTemplateFile = getPlayTemplateFile(psiFile);
        return playTemplateFile;
    }

    private Language getCurrentTemplateLanguage() {
        if (isProjectPresent() && getCurrentPlayTemplateFile() != null) {
            return StdLanguages.HTML;
        }
        return null;
    }
}
