package mumoshu.idea.plugins.play.template.lang;

import com.intellij.lang.Language;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 0:44
 * To change this template use File | Settings | File Templates.
 */
public class PlayTemplateLanguage extends Language {
    public static final Language INSTANCE = new PlayTemplateLanguage();

  public PlayTemplateLanguage() {
    super("Play template");
  }

  @Override
  public boolean isCaseSensitive() {
    return true;
  }
}
