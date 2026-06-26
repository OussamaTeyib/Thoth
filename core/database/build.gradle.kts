plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.thoth.android.library)
}

// Configure Room schema export directory
room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "com.oussamateyib.thoth.core.database"
}

dependencies {
    api(projects.core.model)

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