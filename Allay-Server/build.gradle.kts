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
    // input directory
    from("${rootProject.projectDir}/Data")
    // exclude unpacked folder and block palette.nbt
    exclude("**/unpacked/**")
    exclude("**/block_palette.nbt")
}