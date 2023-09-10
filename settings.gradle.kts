rootProject.name = "Moe Player"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
include(":core:api")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:ui")
include(":features:home")
include(":core:player")
