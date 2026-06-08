pluginManagement {
    repositories {
        maven {
            name = "WagYourMaven"
            url = uri("https://maven.wagyourtail.xyz/releases")
        }
        maven {
            name = "ForgeMaven"
            url = uri("https://maven.minecraftforge.net/")
        }
        maven {
            name = "FabricMaven"
            url = uri("https://maven.fabricmc.net/")
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include("common")
include("network-api:common")

val optionalProjects = listOf(
    "forge-1.8.9",
    "fabric-1.19.4",
    "fabric-1.20.4",
    "fabric-1.20.6",
    "fabric-1.21",
    "fabric-1.21.3",
)

if (System.getenv("JITPACK") == null) {
    val ciTargetProject = System.getenv("BUILD_TARGET_PROJECT")?.trim().orEmpty()

    if (ciTargetProject.isNotEmpty()) {
        require(optionalProjects.contains(ciTargetProject)) {
            "Unknown BUILD_TARGET_PROJECT '$ciTargetProject'. Expected one of: ${optionalProjects.joinToString(", ")}"
        }
        include(ciTargetProject)
    } else {
        optionalProjects.forEach(::include)
    }
}
