plugins {
    id("com.mobilez.android-app")
    id("com.mobilez.android-hilt-convention")
}

android {
    namespace = "com.mobilez.app"
    defaultConfig {
        applicationId = "com.mobilez.app"
    }
}

dependencies {
    implementation(project(":domain:models"))
    implementation(project(":presentation:design-system"))
    implementation(project(":presentation:feature:form-screen"))
    implementation(project(":presentation:feature:result-screen"))

    implementation(project(":infrastructure:repositories"))

    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.compose.material3)

    implementation(libs.androidx.hilt.navigation.compose)
}
