plugins {
    id("com.mobilez.android-lib")
    id("com.mobilez.android-compose-lib")
}

android {
    namespace = "com.mobilez.design"
}

dependencies {

    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.core.ktx)
}
