plugins {
    id("application")
}

description = "Allay-Server"

application {
    mainClass.set("cn.allay.Allay")
}

dependencies {
    implementation(project(":Allay-API"))
    implementation(libs.bundles.log4j.impl)
    implementation(libs.bytebuddy)
    implementation(libs.bundles.terminal)
    testImplementation(libs.bytebuddy)
    testImplementation(libs.bundles.log4j.impl)
}

tasks.processResources {
    // 输入目录
    from("${rootProject.projectDir}/Data")
    // 排除unpacked文件夹
    exclude("**/unpacked/**")
}