plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.oussamateyib.thoth.feature.notes.api"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        checkAllWarnings = true
        warningsAsErrors = true
    }
}

dependencies {
    api(libs.androidx.navigation3.runtime)
    api(libs.kotlinx.serialization.core)
}