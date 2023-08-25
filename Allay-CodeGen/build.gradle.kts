description = "codegen"
group = "cn.allay"
version = "0.0.1"
dependencies {
    implementation(libs.javapoet)
    implementation(libs.nbt)
    implementation(libs.gson)
}

tasks.processResources {
    // input
    from("${rootProject.projectDir}/Data")
    //  exclude unpacked folder
    exclude("**/unpacked/**")
}