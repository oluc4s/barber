plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.sample.jvm.ktor)
}

android {
    namespace = "com.s2start.auth.data"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.firebase.auth)
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.database)
}