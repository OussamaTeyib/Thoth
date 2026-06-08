plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.oussamateyib.thoth.feature.notes.impl"
    compileSdk = 37
}

dependencies {
    implementation(projects.core.designsystem)
    api(projects.core.navigation)
    implementation(projects.feature.notes.api)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.core)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.geometry)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.runtime.compose)
    api(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    api(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.room.common)
    implementation(libs.colorpicker.compose)
    api(libs.dagger)
    implementation(libs.dagger.hilt.core)
    implementation(libs.hilt.android)
    implementation(libs.hilt.lifecycle.viewmodel.compose)
    api(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)

    ksp(libs.hilt.compiler)
    ksp(libs.kotlin.metadata.jvm)
}