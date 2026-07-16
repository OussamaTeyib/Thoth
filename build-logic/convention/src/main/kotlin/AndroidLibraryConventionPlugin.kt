import com.android.build.api.dsl.LibraryExtension
import com.oussamateyib.thoth.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

abstract class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "thoth.android.lint")
            apply(plugin = "thoth.dependency.analysis")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                lint.targetSdk = 37
                // Warn if resources don't start with a unique prefix based on the module path
                resourcePrefix =
                    path.split("""\W"""
                        .toRegex())
                        .drop(1)
                        .distinct()
                        .joinToString(separator = "_")
                        .lowercase() + "_"
            }
        }
    }
}
