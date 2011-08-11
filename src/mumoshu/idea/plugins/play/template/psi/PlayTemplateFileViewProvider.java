package mumoshu.idea.plugins.play.template.psi;

import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.html.HTMLLanguage;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.templateLanguages.TemplateLanguageFileViewProvider;
import mumoshu.idea.plugins.play.template.lang.PlayTemplateLanguage;
import mumoshu.idea.plugins.play.template.lexer.PlayTemplateTokenTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.GroovyFileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/11
 * Time: 10:57
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements TemplateLanguageFileViewProvider {
    private Set myLanguages = null;

    private static Language GROOVY_LANGUAGE = GroovyFileType.GROOVY_LANGUAGE;
    private static Language HTML_LANGUAGE = HTMLLanguage.INSTANCE;

    public PlayTemplateFileViewProvider(PsiManager manager, VirtualFile virtualFile, boolean physical) {
        
        super(manager, virtualFile, physical);
    }

    @NotNull
    @Override
    public Language getBaseLanguage() {
        return PlayTemplateLanguage.INSTANCE;
    }

    @NotNull
    @Override
    public Language getTemplateDataLanguage() {
        return HTML_LANGUAGE;
    }

    /**
     * You should create a deep copy of {@link MultiplePsiFilesPerDocumentFileViewProvider} in this method.<br />
     * <br />
     * On editor open, called from:
     * {@link com.intellij.psi.impl.source.text.BlockSupportImpl#makeFullParse(com.intellij.lang.ASTNode, CharSequence, int, com.intellij.psi.impl.source.PsiFileImpl)}
     * {@link com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider#createCopy(com.intellij.testFramework.LightVirtualFile)}
     * <br />
     * <code>fileCopy</code> would be an instanceof {@link com.intellij.testFramework.LightVirtualFile} which content is
     * same with
     */
    @Override
    protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile fileCopy) {
        PlayTemplateFileViewProvider fileViewProvider = new PlayTemplateFileViewProvider(getManager(), fileCopy, false);
        return fileViewProvider;
    }

    // You must override below two methods too.

    @NotNull
    @Override
    public Set<Language> getLanguages() {
        if (myLanguages == null) {
            myLanguages = new HashSet(3);
            myLanguages.add(PlayTemplateLanguage.INSTANCE);
            myLanguages.add(HTMLLanguage.INSTANCE);
            myLanguages.add(GroovyFileType.GROOVY_LANGUAGE);
        }
        return myLanguages;
    }

    @NotNull
    @Override
    protected PsiFile createFile(Language lang) {

        if(lang == HTML_LANGUAGE)
        {
            ParserDefinition def = (ParserDefinition) LanguageParserDefinitions.INSTANCE.forLanguage(lang);
            PsiFileImpl file = (PsiFileImpl)def.createFile(this);
            /**
             * You msut set original file because templage language lexer is obtained in the below method:
             * {@link com.intellij.psi.templateLanguages.TemplateDataElementType#parseContents(com.intellij.lang.ASTNode)}
             * while creating HTML AST.
             */
            file.setContentElementType(PlayTemplateTokenTypes.PLAY_FRAGMENT_IN_HTML);
            return file;
        }

        if (lang == GROOVY_LANGUAGE) {
            ParserDefinition def = (ParserDefinition) LanguageParserDefinitions.INSTANCE.forLanguage(lang);
            PsiFileImpl file = (PsiFileImpl)def.createFile(this);
            file.setOriginalFile(getPsi(GROOVY_LANGUAGE));
            return file;
        }

        return null;
    }
}
