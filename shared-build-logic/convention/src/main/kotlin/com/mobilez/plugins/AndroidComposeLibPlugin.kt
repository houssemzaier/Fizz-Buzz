package com.mobilez.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeLibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {

            val baseExtension = extensions.getByType<BaseExtension>()
            baseExtension.apply {
                configureCompose(project)
                packagingOptions {
                    exclude("/META-INF/AL2.0")
                    exclude("/META-INF/lgpl2.1")
                }
            }
        }
    }
}

private fun BaseExtension.configureCompose(project: Project) {
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

    buildFeatures.apply {
        compose = true
    }
    composeOptions.apply {
        kotlinCompilerExtensionVersion = libs.findVersion("kotlinCompilerExtension").get().toString()
    }

    project.dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))
    }
}
