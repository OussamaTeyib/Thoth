plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.oussamateyib.thoth.core.designsystem"
    compileSdk = 37
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)
}