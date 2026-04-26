plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    // Application configuration
    namespace = "com.oussamateyib.thoth"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.oussamateyib.thoth"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Java configuration
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Signing configuration
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
            } else {
                // Fallback to debug keystore
                initWith(getByName("debug"))
            }
        }
    }

    // Build types configuration
    buildTypes {
        getByName("release") {
            // Enable code shrinking and obfuscation
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // Remove unused resources
            isShrinkResources = true

            // Apply release signing
            signingConfig = signingConfigs.getByName("release")

            // Exclude dependency metadata
            dependenciesInfo {
                includeInApk = false
                includeInBundle = false
            }

            // Exclude VCS metadata
            vcsInfo.include = false
        }
    }

    // lint configuration
    lint {
        checkAllWarnings = true
        warningsAsErrors = true
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

