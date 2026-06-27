plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dependency.analysis)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

android {
    namespace = "com.oussamateyib.thoth.core.ui"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }

    lint {
        checkAllWarnings = true
        warningsAsErrors = true
    }
}

dependencies {
    implementation(projects.core.designsystem)
    api(projects.core.domain)
    api(projects.core.model)

    implementation(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)
}