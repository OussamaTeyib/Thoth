plugins {
    alias(libs.plugins.thoth.android.library)
    alias(libs.plugins.thoth.hilt)
}

android {
    namespace = "com.oussamateyib.thoth.core.datastore"
}

dependencies {
    api(projects.core.model)

    api(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)
    api(libs.androidx.datastore.preferences.core)
    api(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)
}
