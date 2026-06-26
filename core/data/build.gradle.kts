plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.thoth.android.lint)
    alias(libs.plugins.thoth.dependency.analysis)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

android {
    namespace = "com.oussamateyib.thoth.core.data"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    api(projects.core.database)
    api(projects.core.model)

    api(libs.dagger)
    implementation(libs.dagger.hilt.core)
    compileOnly(libs.hilt.android)
    implementation(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)

    ksp(libs.hilt.compiler)
    ksp(libs.kotlin.metadata.jvm)
}