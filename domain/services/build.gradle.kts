plugins {
    id("com.mobilez.kotlin-lib")
}
dependencies {
    implementation(project(":domain:models"))

    implementation(libs.javax.inject)
    testImplementation(project(":shared:testing"))
}
