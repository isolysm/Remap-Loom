plugins {
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.serialization") version "1.6.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"

    idea
    `kotlin-dsl`
    `maven-publish`
    java
    groovy
    application
    codenarc
}

group = "dev.myosyn"
version = "1.0.0"

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.6.20-1.0.5")

}

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
            id = "dev.myosyn.tools.remap-loom"
            implementationClass = "dev.myosyn.tools.remaploom"
        }
    }
}

codenarc {
    toolVersion = "2.2.0"
    configFile = file("codenarc.groovy")
}

subprojects {
    group = "dev.myosyn.tools.remaploom"
    version = "1.0.0-PRE1" + "-SNAPSHOT"

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


