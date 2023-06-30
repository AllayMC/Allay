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
    implementation(libs.bytebuddy)
    testImplementation(libs.bytebuddy)
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

//Hiding this task should use runShadow
tasks.named("run") {
    group = ""
}

tasks.runShadow {
    jvmArgs?.add("--enable-preview")
}