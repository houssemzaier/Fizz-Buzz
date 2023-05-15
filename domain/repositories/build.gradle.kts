plugins {
    id("com.mobilez.android-lib")
}
android {
    namespace = "com.mobilez.domain.repositories"
}
dependencies {
    implementation(project(":domain:models"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.runtime.ktx)
}
