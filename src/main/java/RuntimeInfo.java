import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RuntimeInfo {

    /**
     * Returns runtime information by looking up classes identifying non-Java JVM
     * languages and appends their major/minor version patch.
     */
    public static String getRuntimeMetadata() {
        StringBuilder s = new StringBuilder();
        String version;

        version= kotlinVersion();
        if (version != null) {
            s.append(",kt=").append(version);
        }

        version = scalaVersion();
        if (version != null) {
            s.append(",sc=").append(version);
        }

        version = clojureVersion();
        if (version != null) {
            s.append(",clj=").append(version);
        }

        version = groovyVersion();
        if (version != null) {
            s.append(",gy=").append(version);
        }

        return s.toString();
    }

    public static String kotlinVersion() {
        try {
            //KotlinVersion.CURRENT.toString()
            Class<?> clazz = Class.forName("kotlin.KotlinVersion");
            Field field = clazz.getField("CURRENT");
            String version = field.get(null).toString();
            return stripPatchRevision(version);

        } catch (Exception t) {
            // ignore
        }
        return null;
    }

    public static String scalaVersion() {
        try {
            // scala.util.Properties.versionNumberString()
            Class<?> clazz = Class.forName("scala.util.Properties");
            Method m = clazz.getMethod("versionNumberString");
            String version = (String) m.invoke(null);
            return stripPatchRevision(version);

        } catch (Exception t) {
            // ignore
        }
        return null;
    }

    public static String clojureVersion() {
        try {
            // (clojure-version) which translates to
            // clojure.core$clojure_version.invokeStatic()
            Class<?> clazz = Class.forName("clojure.core$clojure_version");
            Method m = clazz.getMethod("invokeStatic");
            String version = (String) m.invoke(null);
            return stripPatchRevision(version);

        } catch (Exception t) {
            // ignore
        }
        return null;
    }

    public static String groovyVersion() {
        try {
            // groovy.lang.GroovySystem.getVersion()
            // There's also getShortVersion(), but only since Groovy 3.0.1
            Class<?> clazz = Class.forName("groovy.lang.GroovySystem");
            Method m = clazz.getMethod("getVersion");
            String version = (String) m.invoke(null);
            return stripPatchRevision(version);

        } catch (Exception t) {
            // ignore
        }
        return null;
    }

    static String stripPatchRevision(String version) {
        if (version == null) {
            return null;
        }

        int firstDot = version.indexOf('.');
        int secondDot = version.indexOf('.', firstDot + 1);
        if (secondDot < 0) {
            return version;
        } else {
            return version.substring(0, secondDot);
        }
    }
}
