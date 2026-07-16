import com.oussamateyib.thoth.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
            apply(plugin = "thoth.android.library")

            dependencies {
                "api"(libs.findLibrary("androidx-navigation3-runtime").get())
                "api"(libs.findLibrary("kotlinx-serialization-core").get())
            }
        }
    }
}
