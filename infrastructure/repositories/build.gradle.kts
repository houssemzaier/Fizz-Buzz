plugins {
    id("com.mobilez.android-lib")
    id("com.mobilez.android-hilt-convention")
}
android {
    namespace = "com.mobilez.infrastructure.repositories"
}
dependencies {
    implementation(project(":domain:models"))
    implementation(project(":domain:repositories"))
    implementation(project(":domain:services"))
    implementation(project(":infrastructure:data-source"))

    testImplementation(project(":shared:testing"))
    implementation(libs.androidx.paging.runtime.ktx)
}
