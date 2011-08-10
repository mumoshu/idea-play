package mumoshu.idea.plugins.play.template.fileTypes;

import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.ex.FileTypeIdentifiableByVirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import mumoshu.idea.plugins.play.highlighter.PlayTemplateEditorHighlighter;
import mumoshu.idea.plugins.play.template.lang.PlayTemplateLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PlayTemplateFileType extends LanguageFileType implements FileTypeIdentifiableByVirtualFile {

    public static final PlayTemplateFileType PLAY_TEMPLATE_FILE_TYPE = new PlayTemplateFileType();
    public static final Language PLAY_TEMPLATE_LANGUAGE = PLAY_TEMPLATE_FILE_TYPE.getLanguage();

    @NonNls
    public static final String DEFAULT_EXTENSION = "html";

    private PlayTemplateFileType() {
        super(new PlayTemplateLanguage());
    }

    @Override
    public boolean isMyFileType(VirtualFile file) {
        String extension = file.getExtension();

        return file.getPath().contains("/app/views") && extension != null && file.getExtension().equals("html");
    }

    @NotNull
    @Override
    public String getName() {
        return "Play Template";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "HTML template file for Play framework.";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/mumoshu/idea/plugins/play/images/play-16x16.png");
    }

    @Override
    public String getCharset(@NotNull VirtualFile file, byte[] content) {
        return null;
    }

    public EditorHighlighter getEditorHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors) {
        return new PlayTemplateEditorHighlighter(colors, project, virtualFile);
    }
}
