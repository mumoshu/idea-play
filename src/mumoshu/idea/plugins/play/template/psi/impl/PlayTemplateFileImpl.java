package mumoshu.idea.plugins.play.template.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import mumoshu.idea.plugins.play.template.fileTypes.PlayTemplateFileType;
import mumoshu.idea.plugins.play.template.lang.PlayTemplateLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateFileImpl extends PsiFileBase {

    public PlayTemplateFileImpl(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, PlayTemplateLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return PlayTemplateFileType.PLAY_TEMPLATE_FILE_TYPE;
    }
}
