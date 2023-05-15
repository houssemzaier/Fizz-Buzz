package com.mobilez.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class FeatureLibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            setupPlugins()
            setupDependencies()
        }
    }

    private fun Project.setupPlugins() {
        plugins.apply("com.mobilez.android-lib")
        plugins.apply("com.mobilez.android-compose-lib")
        plugins.apply("com.mobilez.android-hilt-convention")

    }

    private fun Project.setupDependencies() {
        dependencies {
            add("implementation", project(":domain:models"))
            add("implementation", project(":domain:use-cases"))
            add("implementation", project(":presentation:design-system"))
        }
    }
}
