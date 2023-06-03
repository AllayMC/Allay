description = "Allay-CodeGen"

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