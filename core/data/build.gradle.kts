plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.sample.jvm.ktor)
}

android {
    namespace = "com.s2start.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)
    implementation(projects.core.domain)
    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.boom))
    implementation(libs.firebase.firestore)
}