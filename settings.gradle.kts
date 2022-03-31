pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap/")
        maven("https://www.jetbrains.com/intellij-repository/snapshots")
        maven("https://maven.fabricmc.net/")
    }
    resolutionStrategy {
        eachPlugin {
            if(requested.id.id == "org.jetbrains.dokka") {
                useModule("org.jetbrains.dokka:dokka-gradle-plugin:${requested.version}")
            }
            if(requested.id.id == "org.jetbrains.kotlin.jvm") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "remap-loom"

include(":core")
include(":common")

// The two modloaders that exist as of 3/10/2022. 
include(":project:fabric")
include(":project:forge")
// include(":project:quilt")

// Experimental branches
include(":remap")

fun getBuildJavaHome() = System.getProperty("java.home")

gradle.settingsEvaluated {
    if ("true" == System.getProperty("org.gradle.ignoreBuildJavaVersionCheck")) {
        return@settingsEvaluated
    }

    if (!JavaVersion.current().isJava8Compatible) {
        throw GradleException("You're currently using ${getBuildJavaHome()}. Remap-Loom only supports Java versions 8 and up. Please switch to those versions so you can proceed.")
    }
}

val kotlinProjectPath: String? by settings
if (kotlinProjectPath != null) {
    includeBuild(kotlinProjectPath!!) {
        dependencySubstitution {
            substitute(module("org.jetbrains.kotlin:kotlin-compiler")).using(project(":include:kotlin-compiler"))
        }
    }
}