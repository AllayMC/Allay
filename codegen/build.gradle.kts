version = "1.0.0"

dependencies {
    implementation(libs.javapoet)
    implementation(libs.nbt)
    implementation(libs.gson)
    implementation(libs.bytebuddy)
    implementation(libs.javaparser)
    implementation(libs.jsonschema.generator)
    implementation(libs.fastutil)
    implementation(libs.annotations)
}

tasks.publish {
    enabled = false
}