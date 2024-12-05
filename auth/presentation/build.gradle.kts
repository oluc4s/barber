@Suppress("DSL_SCOPE_VIOLATION")
plugins { alias(libs.plugins.sample.android.library) }

android { namespace = "com.s2start.presentation"}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}