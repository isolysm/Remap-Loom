pluginManagement {
    resolutionStrategy {
        eachPlugin {

        }
    }
    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        kotlin("plugins.serialization").version(extra["kotlin.version"] as String)
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Remap-Loom"

include("core")
include("remap")