plugins {
    java
}

group = "net.bluxte"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("org.scala-lang:scala-library:2.13.3")
    implementation("org.clojure:clojure:1.10.1")
    implementation("org.codehaus.groovy:groovy:3.0.7")

    testImplementation("junit", "junit" , "4.12")
}
