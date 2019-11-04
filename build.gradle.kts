plugins {
    kotlin("jvm") version "1.3.50" apply false
}

group = "io.github.iromul"
version = "1.0.0-SNAPSHOT"

subprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
