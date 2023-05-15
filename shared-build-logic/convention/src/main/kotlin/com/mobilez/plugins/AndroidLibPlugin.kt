package com.mobilez.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("unused")
class AndroidLibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            setPlugins()
            val androidExtension: BaseExtension = extensions.getByType()
            setVersioningSDK(androidExtension)
            setJDK(androidExtension)
        }
    }

    private fun setVersioningSDK(androidExtension: BaseExtension) {
        androidExtension.compileSdkVersion(SdKVersionConfig.compileSdk)
        androidExtension.defaultConfig.minSdk = SdKVersionConfig.minSdk
        androidExtension.defaultConfig.targetSdk = SdKVersionConfig.targetSdk
    }

    private fun Project.setPlugins() {
        plugins.apply("com.android.library")
        plugins.apply("org.jetbrains.kotlin.android")
    }

    private fun Project.setJDK(androidExtension: BaseExtension) {
        androidExtension.compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }
        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            add("implementation", kotlin("stdlib-jdk8"))
            add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
        }
    }
}
