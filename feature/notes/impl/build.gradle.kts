plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
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
    namespace = "com.oussamateyib.thoth.feature.notes.impl"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    implementation(projects.core.designsystem)
    api(projects.core.domain)
    api(projects.core.model)
    api(projects.core.navigation)
    implementation(projects.core.ui)
    implementation(projects.feature.notes.api)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.runtime.compose)
    api(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    api(libs.androidx.navigation3.runtime)
    api(libs.dagger)
    implementation(libs.dagger.hilt.core)
    implementation(libs.hilt.android)
    implementation(libs.hilt.lifecycle.viewmodel.compose)
    api(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)

    ksp(libs.hilt.compiler)
    ksp(libs.kotlin.metadata.jvm)
}