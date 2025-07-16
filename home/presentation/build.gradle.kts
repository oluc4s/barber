plugins {
    alias(libs.plugins.sample.android.feature.ui)
    alias(libs.plugins.mapsplatform.secrets.plugin)
    alias(libs.plugins.sample.jvm.ktor)
}

android {
    namespace = "com.s2start.home.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.google.maps.android.compose)
    implementation("com.google.android.gms:play-services-maps:18.2.0") // Google Maps SDK
    implementation("com.google.android.gms:play-services-location:21.0.1") // Para pegar a localização atual
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.home.domain)
    implementation(projects.core.domain)
    implementation("com.google.maps.android:maps-compose:6.4.0")
    implementation("com.google.accompanist:accompanist-placeholder-material:0.32.0")

}