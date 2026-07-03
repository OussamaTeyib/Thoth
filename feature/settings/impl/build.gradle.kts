plugins {
    alias(libs.plugins.thoth.android.feature.impl)
    alias(libs.plugins.thoth.android.library.compose)
    alias(libs.plugins.thoth.hilt)
}

android {
    namespace = "com.oussamateyib.thoth.feature.settings.impl"
}

dependencies {
    api(projects.core.domain)
    api(projects.core.model)
    implementation(projects.core.ui)
    implementation(projects.feature.settings.api)

    implementation(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)
    api(libs.kotlinx.coroutines.core)
}