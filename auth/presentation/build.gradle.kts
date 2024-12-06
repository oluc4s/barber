plugins {
    alias(libs.plugins.sample.android.feature.ui)
}

android { namespace = "com.s2start.presentation"}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}