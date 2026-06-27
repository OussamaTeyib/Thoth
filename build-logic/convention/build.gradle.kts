plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)
    alias(libs.plugins.dependency.analysis)
}

group = "com.oussamateyib.thoth.buildlogic"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.dependency.analysis.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.room.gradle.plugin)
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            severity("fail")
        }
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.thoth.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = libs.plugins.thoth.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidFeatureApi") {
            id = libs.plugins.thoth.android.feature.api.get().pluginId
            implementationClass = "AndroidFeatureApiConventionPlugin"
        }
        register("androidFeatureImpl") {
            id = libs.plugins.thoth.android.feature.impl.get().pluginId
            implementationClass = "AndroidFeatureImplConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.thoth.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.thoth.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLint") {
            id = libs.plugins.thoth.android.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("androidRoom") {
            id = libs.plugins.thoth.android.room.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("dependencyAnalysis") {
            id = libs.plugins.thoth.dependency.analysis.get().pluginId
            implementationClass = "DependencyAnalysisConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.thoth.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("jvmLibrary") {
            id = libs.plugins.thoth.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}