plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("kotlin-lib") {
            id = "com.mobilez.kotlin-lib"
            implementationClass = "com.mobilez.plugins.KotlinLibPlugin"
        }
        register("android-app") {
            id = "com.mobilez.android-app"
            implementationClass = "com.mobilez.plugins.AndroidAppPlugin"
        }
        register("android-lib") {
            id = "com.mobilez.android-lib"
            implementationClass = "com.mobilez.plugins.AndroidLibPlugin"
        }
        register("android-compose-lib") {
            id = "com.mobilez.android-compose-lib"
            implementationClass = "com.mobilez.plugins.AndroidComposeLibPlugin"
        }

        register("android-hilt-convention") {
            id = "com.mobilez.android-hilt-convention"
            implementationClass = "com.mobilez.plugins.AndroidHiltConventionPlugin"
        }
        register("feature-lib") {
            id = "com.mobilez.feature-lib"
            implementationClass = "com.mobilez.plugins.FeatureLibPlugin"
        }
    }
}
