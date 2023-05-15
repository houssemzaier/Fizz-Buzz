@file:Suppress("UnstableApiUsage")

rootProject.name = "Fizz-Buzz"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        includeBuild("shared-build-logic")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

val modulesFeature = arrayOf<String>(
    ":presentation:feature:form-screen",
    ":presentation:feature:result-screen",
)

val modulesPresentation = arrayOf(
    *modulesFeature,
    ":presentation:design-system",
)

val modulesDomain = arrayOf(
    ":domain:models",
    ":domain:use-cases",
    ":domain:repositories",
    ":domain:services",
)

val modulesInfrastructure = arrayOf(
    ":infrastructure:repositories",
    ":infrastructure:data-source",
)
val modulesShared = arrayOf(
    ":shared:testing",
)

val modulesApplications = arrayOf(
    ":apps:app",
    ":apps:catalog",
)

include(
    *modulesApplications,
    *modulesPresentation,
    *modulesDomain,
    *modulesInfrastructure,
    *modulesShared,
)
