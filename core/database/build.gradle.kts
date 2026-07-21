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
    testImplementation(projects.core.testing)

    testImplementation(libs.androidx.test.core)
    implementation(libs.javax.inject)
    testImplementation(libs.junit)
    api(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.robolectric.annotations)
}
