group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

plugins {
    application
    checkstyle
    id("com.github.ben-manes.versions") version "0.52.0"
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}
