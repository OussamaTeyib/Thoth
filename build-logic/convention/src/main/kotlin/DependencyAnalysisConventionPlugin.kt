import com.autonomousapps.DependencyAnalysisExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class DependencyAnalysisConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.autonomousapps.dependency-analysis")

            if (this == rootProject) {
                extensions.configure<DependencyAnalysisExtension> {
                    issues {
                        all {
                            onUnusedDependencies {
                                severity("fail")
                            }
                        }
                    }
                }
            }
        }
    }
}
