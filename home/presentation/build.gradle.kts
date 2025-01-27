plugins {
    alias(libs.plugins.sample.android.feature.ui)
    alias(libs.plugins.mapsplatform.secrets.plugin)
}

android {
    namespace = "com.s2start.home.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.google.maps.android.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation("com.google.maps.android:maps-compose:6.4.0")
}