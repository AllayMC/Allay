version = "1.0.0"

dependencies {
    implementation(project(":server"))
    implementation(libs.javapoet)
    implementation(libs.nbt)
    implementation(libs.gson)
    implementation(libs.jsonschema.generator)
    implementation(libs.fastutil)
    implementation(libs.annotations)
}

tasks.publish {
    enabled = false
}