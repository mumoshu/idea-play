package mumoshu.idea.plugins.play.util;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/10
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class JarUtils {
    @NonNls
    public static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";

    private static String getSpecificationVersion(JarFile jarFile) throws IOException {
        JarEntry jarEntry = jarFile.getJarEntry(MANIFEST_PATH);
        if (jarEntry == null) {
            return null;
        }
        final InputStream inputStream = jarFile.getInputStream(jarEntry);
        Manifest manifest;
        try {
            manifest = new Manifest(inputStream);
        } finally {
            inputStream.close();
        }

        final String version = manifest.getAttributes("Play").getValue((Attributes.Name.SPECIFICATION_VERSION));
        if (version != null) {
            return version;
        }

        return null;
    }

    @Nullable
    public static String getSpecificationVersion(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            try {
                return getSpecificationVersion(jarFile);
            } finally {
                jarFile.close();
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get Specification-Version from a Jar file.
     *
     * @param jarPath Absolute path to a jar file
     * @return
     */
    @Nullable
    public static String getSpecificationVersion(String jarPath) {
        return getSpecificationVersion(new File(jarPath));
    }
}
