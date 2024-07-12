pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = java.net.URI.create("https://www.jitpack.io")
        }

    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
       mavenCentral()
        gradlePluginPortal()
        maven {
            url = java.net.URI.create("https://www.jitpack.io")
        }
    }
}

rootProject.name = "AndroidBaseFrameMVVM"

include(
    ":app",
    ":lib_base",
    ":lib_common",
    ":module_home"
)
include(":module_main")
include(":module_me")
include(":module_notice")
include(":module_search")

