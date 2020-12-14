import org.junit.Assert;
import org.junit.Test;

public class RuntimeInfoTest extends Assert {

    @Test
    public void testRuntimeInfo() {
        assertEquals(",kt=1.4,sc=2.13,clj=3.0,gy=3.0", RuntimeInfo.getRuntimeMetadata());
    }

    @Test
    public void testKotlinVersion() {
        assertEquals("1.4", RuntimeInfo.kotlinVersion());
    }

    @Test
    public void testScalaVersion() {
        assertEquals("2.13", RuntimeInfo.scalaVersion());
    }

    @Test
    public void testClojureVersion() {
        assertEquals("1.10", RuntimeInfo.clojureVersion());
    }

    @Test
    public void testGroovyVersion() {
        assertEquals("3.0", RuntimeInfo.groovyVersion());
    }


    @Test
    public void testStripPatchRevision() {
        assertEquals("1", RuntimeInfo.stripPatchRevision("1"));
        assertEquals("1.2", RuntimeInfo.stripPatchRevision("1.2.3"));

        // Test language-specific representations of non-final versions

        // Kotlin
        assertEquals("1.4", RuntimeInfo.stripPatchRevision("1.4.20-M2"));
        assertEquals("1.3", RuntimeInfo.stripPatchRevision("1.3.0-rc-198"));

        // Scala
        assertEquals("2.13", RuntimeInfo.stripPatchRevision("2.13.0-RC3"));
        assertEquals("2.13", RuntimeInfo.stripPatchRevision("2.13.0-M5-6e0cba7"));
        assertEquals("2.11", RuntimeInfo.stripPatchRevision("2.11.8-18269ea"));

        // Clojure
        assertEquals("1.10", RuntimeInfo.stripPatchRevision("1.10.2-alpha1"));

        // Groovy
        assertEquals("3.0", RuntimeInfo.stripPatchRevision("3.0.0-rc-3"));
    }
}
