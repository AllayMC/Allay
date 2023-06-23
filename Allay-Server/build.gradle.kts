plugins {
    id("application")
    id("me.champeau.jmh") version ("0.7.1")
}

description = "Allay-Server"

application {
    mainClass.set("cn.allay.server.Allay")
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
    //todo Confirm that the state hash is valid and remove it
    //exclude("**/block_palette.nbt")
}

tasks.jmh {
    jvmArgs.add("--enable-preview")
}