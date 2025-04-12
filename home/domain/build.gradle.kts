plugins {
    alias(libs.plugins.sample.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}