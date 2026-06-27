plugins {
    alias(libs.plugins.thoth.android.library)
    alias(libs.plugins.thoth.android.library.compose)
}

android {
    namespace = "com.oussamateyib.thoth.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
    api(projects.core.domain)
    api(projects.core.model)

    api(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)
}