import com.oussamateyib.thoth.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureImplConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "thoth.android.library")

            dependencies {
                "api"(project(":core:navigation"))
                "api"(libs.findLibrary("androidx-navigation3-runtime").get())
            }
        }
    }
}