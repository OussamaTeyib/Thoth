plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.oussamateyib.thoth.feature.notes.api"
    compileSdk = 37
}

dependencies {
    api(libs.androidx.navigation3.runtime)
    api(libs.kotlinx.serialization.core)
}