package splitties.wff

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

@Suppress("unused") // referenced in build.gradle.kts
class WatchfaceLibPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyPlugin()
    }

    private fun Project.applyPlugin() {
        plugins.apply("org.jetbrains.kotlin.jvm")
        sourceSets {
            main { kotlin.srcDir(projectDir.resolve("src")) }
        }
        kotlin { compilerOptions.freeCompilerArgs.add("-Xcontext-receivers") }
        dependencies {
            "implementation"("org.splitties.wff:core:$thisProjectVersion")
        }
        val extension = extensions.create<WatchfaceLibExtension>("watchFaceLib")
        val doNotUseExtensions by extension.doNotUseExtensions.convention(false)
        afterEvaluate {
            val useExtensions = doNotUseExtensions.not()
            if (useExtensions) dependencies {
                "implementation"("org.splitties.wff:extensions:$thisProjectVersion")
            }
        }
    }

    //region Code normally generated by Gradle when used in .gradle.kts scripts.

    private inline fun Project.sourceSets(configure: SourceSetContainer.() -> Unit) {
        extensions.getByName<SourceSetContainer>("sourceSets").configure()
    }

    private fun SourceSetContainer.main(configure: SourceSet.() -> Unit) {
        named("main").configure(configure)
    }

    private val SourceSet.kotlin: SourceDirectorySet get() = extensions.getByName<SourceDirectorySet>("kotlin")

    private inline fun Project.kotlin(
        configure: KotlinJvmProjectExtension.() -> Unit
    ) {
        extensions.getByName<KotlinJvmProjectExtension>("kotlin").apply(configure)
    }
    //endregion
}
