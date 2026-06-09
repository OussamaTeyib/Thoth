plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

android {
    namespace = "com.oussamateyib.thoth.core.domain"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
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