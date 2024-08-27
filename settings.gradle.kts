pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Job Search"
include(":app")
include(":input")
include(":core")
include(":search")
include(":favourites")
include(":responses")
include(":messages")
include(":profile")
include(":core:database")
include(":core:adapter")
include(":core:model")
include(":core:network")
include(":details")
