import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

plugins {
    id("application")
}

description = "Allay-WorldConvert"

application {
    mainClass.set("cn.allay.worldconvert.WorldConverter")
}

dependencies {
    implementation(project(":Allay-API"))
    implementation(libs.hephaistos)
    implementation(libs.picocli)
    implementation(libs.progressbar)
    implementation(libs.bundles.logging)
    implementation(files("lib/KotlinLib.jar"))
}

//Hiding this task should use runShadow
tasks.named("run") {
    group = ""
}

tasks.runShadow {
    jvmArgs?.add("--enable-preview")
}

//disable ,please use `java -jar` to start directly
tasks.startScripts {
    group = ""
    enabled = false
}
//disable
tasks.startShadowScripts {
    group = ""
    enabled = false
}

tasks.shadowJar {
    transform(Log4j2PluginsCacheFileTransformer())
}