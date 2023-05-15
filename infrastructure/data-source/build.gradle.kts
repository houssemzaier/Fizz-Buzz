plugins {
    id("com.mobilez.android-lib")
    id("com.mobilez.android-hilt-convention")
}
android {
    namespace = "com.mobilez.infrastructure.data_source"
}
dependencies {
    implementation(project(":domain:models"))
    implementation(project(":domain:services"))

    testImplementation(project(":shared:testing"))
    implementation(libs.androidx.paging.runtime.ktx)
}
