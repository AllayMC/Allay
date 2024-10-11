version = "1.0.0"

dependencies {
    implementation(libs.javapoet)
    implementation(libs.nbt)
    implementation(libs.gson)
    implementation(libs.bundles.fastutil)
    implementation(libs.annotations)
}

tasks.publish {
    enabled = false
}