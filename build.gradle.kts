// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.JSON
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF

plugins {
    alias(libs.plugins.ktlint) apply true
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
}

tasks.register("checkJavaVersion") {
    doLast {
        println("Gradle is using Java version: ${JavaVersion.current()}")
        println("Gradle is using Java home: ${System.getProperty("java.home")}")
    }
}

tasks.register("cleanAll", Delete::class) {
    delete(rootProject.buildDir)
    allprojects.forEach { project ->
        delete(project.buildDir)
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        android.set(true)
        outputColorName.set("RED")
        disabledRules.set(listOf("no-wildcard-imports"))
        verbose.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(false)

        reporters {
            reporter(HTML)
            reporter(CHECKSTYLE)
            reporter(SARIF)
            reporter(PLAIN)
            reporter(JSON)
        }
    }

    tasks.register("runDefault", Exec::class.java) {
        dependsOn(":apps:app:installDebug")

        val packageName = "com.mobilez.app"
        val mainActivity = "com.mobilez.app.MainActivity"

        doFirst {
            commandLine("adb", "shell", "am", "start", "-n", "$packageName/$mainActivity")
        }
    }
}
