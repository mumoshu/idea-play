package mumoshu.idea.plugins.play.config;

import com.intellij.openapi.roots.libraries.LibraryProperties;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.Nullable;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class PlayLibraryProperties extends LibraryProperties<PlayLibraryProperties> {
  private String myVersion;

  public PlayLibraryProperties(String version) {
    myVersion = version;
  }

  @Nullable
  public String getVersion() {
    return myVersion;
  }

  @Override
  public PlayLibraryProperties getState() {
    return null;
  }

  @Override
  public void loadState(PlayLibraryProperties state) {
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof PlayLibraryProperties && Comparing.equal(myVersion, ((PlayLibraryProperties) obj).myVersion);
  }

  @Override
  public int hashCode() {
    return myVersion != null ? myVersion.hashCode() : 0;
  }
}