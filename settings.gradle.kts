apply {
    from("${rootDir}/sample/settings.gradle.kts")
}

rootProject.name = "barber"
include(":app")
include(":home:presentation")
include(":home:data")
include(":home:domain")
