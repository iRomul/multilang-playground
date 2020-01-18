plugins {
    kotlin("jvm")
    application
}

group = "io.github.iromul.media"
version = "1.0.0-SNAPSHOT"

application {
    mainClassName = "io.github.iromul.media.CliKt"
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/ktor")
    maven("https://dl.bintray.com/kotlin/kotlinx")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jaudiotagger:jaudiotagger:2.0.1")
    implementation("com.github.ajalt:clikt:2.3.0")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("io.github.microutils:kotlin-logging:1.7.7")
    implementation("io.ktor:ktor-client-apache:1.2.6")
    implementation("io.ktor:ktor-client-jackson:1.2.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.+")
    implementation("net.coobird:thumbnailator:[0.4, 0.5)")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
