plugins {
    alias(libs.plugins.thoth.android.library)
}

android {
    namespace = "com.oussamateyib.thoth.core.testing"
}

dependencies {
    api(projects.core.model)

    api(libs.androidx.test.runner)
    implementation(libs.hilt.android.testing)
    api(libs.junit)
    implementation(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.test)
}
