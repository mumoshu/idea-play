package mumoshu.idea.plugins.play.template;

import com.intellij.codeInsight.template.FileTypeBasedContextType;
import mumoshu.idea.plugins.play.PlayTemplateFileType;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 0:32
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateContextType extends FileTypeBasedContextType {

  protected PlayTemplateContextType() {
    super("PLAYTEMPLATE", "PlayTemplate", PlayTemplateFileType.PLAY_TEMPLATE_FILE_TYPE);
  }

}