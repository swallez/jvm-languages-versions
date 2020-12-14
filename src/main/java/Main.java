public class Main {
    public static void main(String[] args) {

        System.out.println("Java version " + System.getProperty("java.specification.version"));
        System.out.println("Kotlin version " + RuntimeInfo.kotlinVersion());
        System.out.println("Scala version " + RuntimeInfo.scalaVersion());
        System.out.println("Clojure version " + RuntimeInfo.clojureVersion());
        System.out.println("Groovy version "+ RuntimeInfo.groovyVersion());
    }
}
