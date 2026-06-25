plugins {
    `kotlin-dsl`
}

group = "com.oussamateyib.thoth.buildlogic"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation(libs.dependency.analysis.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("dependencyAnalysis") {
            id = libs.plugins.thoth.dependency.analysis.get().pluginId
            implementationClass = "DependencyAnalysisConventionPlugin"
        }
    }
}