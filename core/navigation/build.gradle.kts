plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.oussamateyib.thoth.core.navigation"
    compileSdk = 37
}

dependencies {
    api(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.saveable)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    api(libs.androidx.navigation3.runtime)
}