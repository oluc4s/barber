plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.sample.jvm.ktor)
}

android {
    namespace = "com.s2start.home.data"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.boom))
    implementation(libs.firebase.firestore)

    implementation(projects.home.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.database)
}