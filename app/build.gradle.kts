group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
}

plugins {
    application
    checkstyle
    id("com.github.ben-manes.versions") version "0.52.0"
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}
