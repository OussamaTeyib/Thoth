import com.oussamateyib.thoth.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")
            apply(plugin = "thoth.android.lint")
            apply(plugin = "thoth.dependency.analysis")

            configureKotlinJvm()
        }
    }
}
