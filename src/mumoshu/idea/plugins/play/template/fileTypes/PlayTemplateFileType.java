package mumoshu.idea.plugins.play.template.fileTypes;

import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import mumoshu.idea.plugins.play.PlayIcons;
import mumoshu.idea.plugins.play.PlayTemplateLanguage;
import mumoshu.idea.plugins.play.highlighter.PlayTemplateEditorHighlighter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 0:35
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateFileType extends LanguageFileType {

  public static final PlayTemplateFileType PLAY_TEMPLATE_FILE_TYPE = new PlayTemplateFileType();
  public static final Language PLAY_TEMPLATE_LANGUAGE = PLAY_TEMPLATE_FILE_TYPE.getLanguage();
  @NonNls
  public static final String DEFAULT_EXTENSION = "html";

  private PlayTemplateFileType() {
    super(new PlayTemplateLanguage());
  }

  @NotNull
  @NonNls
  public String getName() {
    return "Play template";
  }

  @NonNls
  @NotNull
  public String getDescription() {
    return "Play templates";
  }

  @NotNull
  @NonNls
  public String getDefaultExtension() {
    return DEFAULT_EXTENSION;
  }

  public Icon getIcon() {
    return PlayIcons.PLAY_TEMPLATE_16x16;
  }

  public boolean isJVMDebuggingSupported() {
    return false;
  }

  public EditorHighlighter getEditorHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors) {
    return new PlayTemplateEditorHighlighter(colors, project, virtualFile);
  }
}
