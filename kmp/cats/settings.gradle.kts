pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("multiplatform") version "1.9.10"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
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