plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.thoth.android.library)
}

android {
    namespace = "com.oussamateyib.thoth.feature.notes.api"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
    api(libs.kotlinx.serialization.core)
}