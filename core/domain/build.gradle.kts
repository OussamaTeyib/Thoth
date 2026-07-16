plugins {
    alias(libs.plugins.thoth.android.library)
}

android {
    namespace = "com.oussamateyib.thoth.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    api(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)
}
