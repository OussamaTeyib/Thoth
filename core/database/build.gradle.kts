plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

// Configure Room schema export directory
room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "com.oussamateyib.thoth.core.database"
    compileSdk = 37
}

dependencies {
    implementation(libs.androidx.room.common)
    api(libs.androidx.room.runtime)
    implementation(libs.androidx.sqlite)
    api(libs.dagger)
    implementation(libs.dagger.hilt.core)
    implementation(libs.hilt.android)
    implementation(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)

    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.compiler)
    ksp(libs.kotlin.metadata.jvm)
}