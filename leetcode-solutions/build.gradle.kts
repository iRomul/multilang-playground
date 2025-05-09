plugins {
    java
}

dependencies {
    compileOnly("org.jetbrains:annotations:16.0.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")

    testImplementation("org.assertj:assertj-core:3.13.2")
}

