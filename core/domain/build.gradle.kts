plugins {
    alias(libs.plugins.thoth.android.library)
}

android {
    namespace = "com.oussamateyib.thoth.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)
    testImplementation(projects.core.testing)

    api(libs.javax.inject)
    testImplementation(libs.junit)
    api(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}
