plugins {
    java
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    implementation("com.opencsv:opencsv:5.9")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.assertj:assertj-core:3.13.2")
}
