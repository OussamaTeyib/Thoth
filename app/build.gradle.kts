plugins {
    alias(libs.plugins.thoth.android.application)
    alias(libs.plugins.thoth.android.application.compose)
    alias(libs.plugins.thoth.hilt)
}

// Detect if the current build is for an Android App Bundle (AAB)
val isBuildingBundle = gradle.startParameter.taskNames.any { it.lowercase().contains("bundle") }

android {
    namespace = "com.oussamateyib.thoth"

    defaultConfig {
        applicationId = "com.oussamateyib.thoth"
        versionCode = 1
        versionName = "1.0.0"
    }

    splits {
        abi {
            // Disable ABI splits when building an App Bundle to avoid conflicts
            isEnable = !isBuildingBundle
            // Reset previous ABI split configuration
            reset()
            // Specify the supported ABIs
            include("x86", "x86_64", "armeabi-v7a", "arm64-v8a")
            // Generate a universal APK when ABI splits are enabled
            isUniversalApk = true
        }
    }

    signingConfigs {
        create("release") {
            // Use environment variables if provided
            if (System.getenv("STORE_FILE") != null &&
                System.getenv("STORE_PASSWORD") != null &&
                System.getenv("KEY_ALIAS") != null
            ) {
                storeFile = file(System.getenv("STORE_FILE"))
                storePassword = System.getenv("STORE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD") ?: System.getenv("STORE_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            // Enable code shrinking and obfuscation
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // Remove unused resources
            isShrinkResources = true

            // Apply release signing
            signingConfig = if (System.getenv("STORE_FILE") != null) {
                signingConfigs.getByName("release")
            } else {
                signingConfigs.getByName("debug") // Fallback to debug keystore
            }

            // Exclude dependency metadata
            dependenciesInfo {
                includeInApk = false
                includeInBundle = false
            }

            // Exclude VCS metadata
            vcsInfo.include = false
        }
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.feature.notes.api)
    implementation(projects.feature.notes.impl)
    implementation(projects.feature.settings.api)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.compose.adaptive)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.annotation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.kotlinx.coroutines.core)
}