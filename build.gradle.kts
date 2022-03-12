plugins {
    java
    groovy
    `maven-publish`
    kotlin("jvm") apply false
}

sourceCompatibility = 17
targetCompatibility = 17

repositories{
    jcenter()
}

publishing {
    publications {
        register<MavenPublication>("remap-loom") {
            group.id = "xyz.myosyn"
        }
    }
}