plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.oussamateyib.thoth.core.navigation"
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
    api(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.saveable)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    api(libs.androidx.navigation3.runtime)
}