plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    idea
    `kotlin-dsl`
    `maven-publish`
    java
    groovy
    application
}

group = "dev.myosyn"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build.generated/ksp/test/kotlin")
    }
}

gradlePlugin {
    plugins {
        register("remap-loom") {
            id = "dev.myosyn.remaploom"
            implementationClass = "dev.myosyn.gradle.remaploom.RemapLoomPlugin"
        }
    }
}

subprojects {
    group = "dev.myosyn.tools.remaploom"
    version = "1.0.0-PRE1"

    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            sourceCompatibility = JavaVersion.VERSION_17.toString()
            targetCompatibility = JavaVersion.VERSION_17.toString()
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

}


