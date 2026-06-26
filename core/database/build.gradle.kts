plugins {
    alias(libs.plugins.thoth.android.library)
    alias(libs.plugins.thoth.android.room)
}

android {
    namespace = "com.oussamateyib.thoth.core.database"
}

dependencies {
    api(projects.core.model)

    api(libs.dagger)
    implementation(libs.dagger.hilt.core)
    implementation(libs.hilt.android)
    implementation(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)

    ksp(libs.hilt.compiler)
    ksp(libs.kotlin.metadata.jvm)
}