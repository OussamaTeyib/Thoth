import com.oussamateyib.thoth.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.devtools.ksp")

            dependencies {
                "implementation"(libs.findLibrary("dagger-hilt-core").get())
                "ksp"(libs.findLibrary("hilt-compiler").get())
                "ksp"(libs.findLibrary("kotlin-metadata-jvm").get())
            }

            // Pure JVM modules
            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies {
                    "api"(libs.findLibrary("dagger").get())
                }
            }

            // Android modules
            pluginManager.withPlugin("com.android.base") {
                apply(plugin = "dagger.hilt.android.plugin")
                dependencies {
                    if (pluginManager.hasPlugin("com.android.application")) {
                        "implementation"(libs.findLibrary("dagger").get())
                        "implementation"(libs.findLibrary("hilt-android").get())
                    } else {
                        "api"(libs.findLibrary("dagger").get())
                        "compileOnly"(libs.findLibrary("hilt-android").get())
                    }
                }
            }
        }
    }
}
