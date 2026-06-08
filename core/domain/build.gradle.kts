plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
}

android {
    namespace = "com.oussamateyib.thoth.core.domain"
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
    api(projects.core.data)
    api(projects.core.model)

    api(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)
}