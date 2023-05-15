package com.mobilez.plugins

object AndroidAppConfig {
    const val versionCode: Int = 1
    const val versionName: String = "1.0.0"
    val sdkVersionConfig: SdKVersionConfig = SdKVersionConfig
}

object SdKVersionConfig {
    const val compileSdk: Int = 33
    const val minSdk: Int = 24
    const val targetSdk: Int = 33
}
