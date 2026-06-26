plugins {
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.thoth.android.library)
}

android {
    namespace = "com.oussamateyib.thoth.core.data"
}

dependencies {
    api(projects.core.database)
    api(projects.core.model)

    api(libs.dagger)
    implementation(libs.dagger.hilt.core)
    compileOnly(libs.hilt.android)
    implementation(libs.javax.inject)
    api(libs.kotlinx.coroutines.core)

    ksp(libs.hilt.compiler)
    ksp(libs.kotlin.metadata.jvm)
}