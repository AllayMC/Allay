description = "Allay-CodeGen"

dependencies {
    implementation(project(":Allay-API"))
    implementation(libs.javapoet)
}

tasks.processResources {
    // input
    from("${rootProject.projectDir}/Data")
    //  exclude unpacked folder
    exclude("**/unpacked/**")
}