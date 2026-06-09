plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

// Configure Room schema export directory
room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "com.oussamateyib.thoth.core.database"
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