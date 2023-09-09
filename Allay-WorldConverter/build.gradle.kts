import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

plugins {
    id("application")
}

description = "worldconverter"
group = "cn.allay"
version = "0.0.1"

application {
    mainClass.set("cn.allay.worldconverter.WorldConverter")
}

dependencies {
    implementation(project(":Allay-Server"))
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

tasks.distTar { enabled = false }
tasks.distZip { enabled = false }
tasks.shadowDistTar { enabled = false }
tasks.shadowDistZip { enabled = false }


tasks.shadowJar {
    transform(Log4j2PluginsCacheFileTransformer())
}