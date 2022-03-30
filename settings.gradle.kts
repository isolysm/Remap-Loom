pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://jitpack.io")
        maven("https://maven.fabricmc.net/")
    }
    resolutionStrategy {
        eachPlugin {
            if(requested.id.id == "org.jetbrains.dokka") {
                useModule("org.jetbrains.dokka:dokka-gradle-plugin:${requested.version}")
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

gradle.settingsEvalutated {
    if ("true" == System.getProperty("org.gradle.ignoreBuildJavaVersionCheck")) {
        return@settingsEvalutated
    }

    if (!JavaVersion.current().isJava8Compatible) {
        throw GradleException("You're currently using ${getBuildJavaHome()}. Remap-Loom only supports Java versions 8 and up. Please switch to those versions so you can proceed.")
    }
}