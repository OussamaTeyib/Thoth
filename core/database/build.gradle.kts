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
    androidTestImplementation(projects.core.testing)
    testImplementation(projects.core.testing)

    androidTestImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.core)
    implementation(libs.javax.inject)
    androidTestImplementation(libs.junit)
    testImplementation(libs.junit)
    api(libs.kotlinx.coroutines.core)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.robolectric.annotations)
}
