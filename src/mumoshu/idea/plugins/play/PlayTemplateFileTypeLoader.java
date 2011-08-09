package mumoshu.idea.plugins.play;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 0:34
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateFileTypeLoader extends FileTypeFactory {
  public static final List<FileType> PLAY_TEMPLATE_FILE_TYPES = new ArrayList<FileType>();

  public static FileType[] getGroovyEnabledFileTypes() {
    return PLAY_TEMPLATE_FILE_TYPES.toArray(new FileType[PLAY_TEMPLATE_FILE_TYPES.size()]);
  }

  public static Set<String> getCustomPlayTemplateExtensions() {
    final LinkedHashSet<String> strings = new LinkedHashSet<String>();
    strings.add("json");
    strings.add("xml");
    strings.add("xls");
//    for (GroovyScriptTypeDetector ep : GroovyScriptTypeDetector.EP_NAME.getExtensions()) {
//      Collections.addAll(strings, ep.getExtensions());
//    }
    return strings;
  }

  public static List<String> getAllPlayTemplateExtensions() {
    final ArrayList<String> strings = new ArrayList<String>();
    strings.add(PlayTemplateFileType.DEFAULT_EXTENSION);
    strings.addAll(getCustomPlayTemplateExtensions());
    return strings;
  }

  public void createFileTypes(@NotNull FileTypeConsumer consumer) {
    consumer.consume(PlayTemplateFileType.PLAY_TEMPLATE_FILE_TYPE, StringUtil.join(getAllPlayTemplateExtensions(), ";"));
    PLAY_TEMPLATE_FILE_TYPES.add(PlayTemplateFileType.PLAY_TEMPLATE_FILE_TYPE);
  }
}