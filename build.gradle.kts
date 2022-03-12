plugins {
    java
    groovy
    `kotlin-dsl`
    `maven-publish`
    kotlin("jvm") apply false
}

val kotestVersion: String by project.extra

gradlePlugin {
    plugins {
        register("remap-loom") {
            id = "gg.myosyn.remaploom"
            implementationClass = "gg.myosyn.remaploom.RemapLoomPlugin"
        }
    }
}
