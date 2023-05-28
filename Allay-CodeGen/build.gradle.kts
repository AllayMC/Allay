description = "Allay-CodeGen"

dependencies {
    implementation(project(":Allay-API"))
    implementation(libs.javapoet)
}

tasks.processResources {
    // 输入目录
    from("${rootProject.projectDir}/Data")
    // 排除unpacked文件夹
    exclude("**/unpacked/**")
}