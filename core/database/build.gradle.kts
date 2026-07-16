plugins {
    alias(libs.plugins.thoth.android.library)
    alias(libs.plugins.thoth.android.room)
    alias(libs.plugins.thoth.hilt)
}

android {
    namespace = "com.oussamateyib.thoth.core.database"
}

dependencies {
    api(projects.core.model)

    implementation(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)
}
