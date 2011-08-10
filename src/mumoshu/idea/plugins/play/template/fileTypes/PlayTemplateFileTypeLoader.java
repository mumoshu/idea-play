package mumoshu.idea.plugins.play.template.fileTypes;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
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

  public void createFileTypes(@NotNull FileTypeConsumer consumer) {
    consumer.consume(PlayTemplateFileType.PLAY_TEMPLATE_FILE_TYPE);
    PLAY_TEMPLATE_FILE_TYPES.add(PlayTemplateFileType.PLAY_TEMPLATE_FILE_TYPE);
  }
}