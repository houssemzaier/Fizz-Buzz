plugins {
    id("com.mobilez.android-lib")
    id("com.mobilez.android-hilt-convention")
}
android {
    namespace = "com.mobilez.domain.usecases"
}

dependencies {
    implementation(project(":domain:models"))
    implementation(project(":domain:repositories"))

    testImplementation(project(":shared:testing"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.runtime.ktx)
}
