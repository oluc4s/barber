plugins {
    alias(libs.plugins.sample.jvm.library)
    alias(libs.plugins.sample.jvm.ktor)
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
}