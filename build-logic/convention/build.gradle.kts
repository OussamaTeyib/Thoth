plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)
}

group = "com.oussamateyib.thoth.buildlogic"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.dependency.analysis.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.thoth.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLint") {
            id = libs.plugins.thoth.android.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("dependencyAnalysis") {
            id = libs.plugins.thoth.dependency.analysis.get().pluginId
            implementationClass = "DependencyAnalysisConventionPlugin"
        }
    }
}