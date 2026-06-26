plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.thoth.android.lint)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}