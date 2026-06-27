plugins {
    alias(libs.plugins.thoth.android.library)
    alias(libs.plugins.thoth.android.library.compose)
}

android {
    namespace = "com.oussamateyib.thoth.core.navigation"
}

dependencies {
    api(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.saveable)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    api(libs.androidx.navigation3.runtime)
}