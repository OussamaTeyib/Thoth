plugins {
    alias(libs.plugins.thoth.android.library)
    alias(libs.plugins.thoth.android.library.compose)
}

android {
    namespace = "com.oussamateyib.thoth.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.annotation)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.text)
}