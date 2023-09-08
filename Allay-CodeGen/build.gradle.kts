description = "codegen"
group = "cn.allay"
version = "0.0.1"
dependencies {
    implementation(libs.javapoet)
    implementation(libs.nbt)
    implementation(libs.gson)
    implementation(libs.fastutil)
    implementation(libs.annotations)
}

tasks.processResources {
    // input
    from("${rootProject.projectDir}/Allay-Data/resources")
    // exclude folder
    exclude("**/unpacked/**")
    exclude("**/updater/**")
}