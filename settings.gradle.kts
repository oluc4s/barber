pluginManagement {
    includeBuild("build-logic")
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
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "barber"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":auth:presentation")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":auth:domain")
include(":core:domain")
include(":auth:data")
include(":core:data")
include(":core:database")
include(":home:presentation")
include(":home:data")
include(":home:domain")
