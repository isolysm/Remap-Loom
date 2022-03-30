plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    java
    groovy
    scala
}

dependencies {

}

gradlePlugin {
    plugins {
        fabricLoom {
            id = "gg.myosyn"
            implementationclass = "net.fabricmc.loom.bootstrap.LoomGradlePluginBootstrap"
        }
    }
}