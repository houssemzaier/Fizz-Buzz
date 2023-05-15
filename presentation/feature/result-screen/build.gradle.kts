plugins {
    id("com.mobilez.feature-lib")
}
android {
    namespace = "com.mobilez.feature.result"
}

dependencies {
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    testImplementation(project(":shared:testing"))
}
