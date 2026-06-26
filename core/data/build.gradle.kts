plugins {
    alias(libs.plugins.thoth.android.library)
    alias(libs.plugins.thoth.hilt)
}

android {
    namespace = "com.oussamateyib.thoth.core.data"
}

dependencies {
    api(projects.core.database)
    api(projects.core.model)

    implementation(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)
}