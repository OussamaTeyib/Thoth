plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.oussamateyib.thoth.core.data"
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
    api(projects.core.database)
    api(projects.core.model)

    api(libs.dagger)
    implementation(libs.dagger.hilt.core)
    implementation(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)
}