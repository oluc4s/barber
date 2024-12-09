plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.sample.android.room)
}

android {
    namespace = "com.s2start.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}