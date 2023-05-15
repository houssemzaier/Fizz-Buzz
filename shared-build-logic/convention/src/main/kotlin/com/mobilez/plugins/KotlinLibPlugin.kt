package com.mobilez.plugins

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class KotlinLibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            setupPlugins()
            setupJDK()
        }
    }

    private fun Project.setupPlugins() {
        plugins.apply("org.jetbrains.kotlin.jvm")
        dependencies {
            add("implementation", kotlin("stdlib-jdk8"))
        }
    }

    private fun Project.setupJDK() {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
        tasks.withType<org.gradle.api.tasks.compile.JavaCompile> {
            sourceCompatibility = JavaVersion.VERSION_17.toString()
            targetCompatibility = JavaVersion.VERSION_17.toString()
        }
    }
}
