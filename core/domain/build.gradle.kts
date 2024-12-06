plugins {
    alias(libs.plugins.sample.jvm.library)
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
}