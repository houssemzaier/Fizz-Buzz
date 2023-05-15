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

class AndroidAppPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            setupPlugins()

            val baseExtension = extensions.getByType<BaseExtension>()
            baseExtension.apply {
                setVersioningSDK(this)

                defaultConfig {
                    versionCode = AndroidAppConfig.versionCode
                    versionName = AndroidAppConfig.versionName
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables.useSupportLibrary = true
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }

                setJDK(baseExtension)
                configureCompose(project)

                packagingOptions {
                    exclude("/META-INF/AL2.0")
                    exclude("/META-INF/lgpl2.1")
                }
            }
        }
    }
}

private fun Project.setupPlugins() = plugins.apply {
    apply("com.android.application")
    apply("kotlin-android")
    apply("kotlin-kapt")
}

private fun setVersioningSDK(androidExtension: BaseExtension) {
    androidExtension.compileSdkVersion(SdKVersionConfig.compileSdk)
    androidExtension.defaultConfig.minSdk = SdKVersionConfig.minSdk
    androidExtension.defaultConfig.targetSdk = SdKVersionConfig.targetSdk
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
