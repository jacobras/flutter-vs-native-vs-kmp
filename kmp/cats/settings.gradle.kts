pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("multiplatform") version "1.8.21"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
        id("org.jetbrains.compose") version "1.4.3"
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Cats"
include(":app")
include(":shared")
include(":macrobenchmark")