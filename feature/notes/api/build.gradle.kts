plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.thoth.android.lint)
    alias(libs.plugins.thoth.dependency.analysis)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

android {
    namespace = "com.oussamateyib.thoth.feature.notes.api"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    api(libs.androidx.navigation3.runtime)
    api(libs.kotlinx.serialization.core)
}