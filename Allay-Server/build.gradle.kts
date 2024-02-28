import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

plugins {
    `java-library`
    application
    id("me.champeau.jmh") version "0.7.2"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
}

group = "org.allaymc"
description = "server"
version = "0.0.1"

application {
    mainClass.set("org.allaymc.server.Allay")
}

dependencies {
    api(project(":Allay-API"))
    implementation(libs.mcterminal)
    implementation(libs.bundles.logging)
    implementation(libs.disruptor)
    implementation(libs.progressbar) {
        exclude(group = "org.apache.logging.log4j", module = "log4j-core")
    }
    implementation(libs.bytebuddy) {
        exclude(group = "org.jline", module = "jline")
    }
    implementation(libs.libdeflate)
    implementation(libs.leveldbjni)
    implementation(libs.netty.epoll)
    implementation(libs.netty.kqueue)
    implementation(libs.jegenerator)
    implementation(libs.fastreflect)
    implementation(libs.reflections)
    implementation(libs.caffeine)
    implementation(libs.polyglot)
    implementation(libs.js)
    implementation(libs.bundles.graalvm)
    implementation(libs.oshi)

    testImplementation(libs.bytebuddy)
}

gitProperties {
    gitPropertiesName = "git.properties"
    gitPropertiesResourceDir.set(file("${rootProject.projectDir}/Allay-Data/resources"))
}

tasks.processResources {
    dependsOn("generateGitProperties")
    // input directory
    from("${rootProject.projectDir}/Allay-Data/resources")
    // exclude unpacked folder and block palette.nbt
    exclude("**/unpacked/**")
}

// disable, please use `java -jar` to start directly
tasks.startScripts {
    enabled = false
}
// disable
tasks.startShadowScripts {
    enabled = false
}
// disable
tasks.distTar {
    enabled = false
}
// disable
tasks.distZip {
    enabled = false
}

tasks.sourcesJar {
    dependsOn("generateGitProperties")
}

tasks.jmh {
    // Add the executed test case
    includes.add("AABBTreeJMHTest")
    // includes.add("BlockStateUpdateJMHTest")
    // includes.add("ChunkJMHTest")
    // includes.add("ThroughList2Array")
}

tasks.runShadow {
    val runningDir = File("${rootProject.projectDir}/run/")
    runningDir.mkdirs()
    workingDir = runningDir
    this.jarFile = file("build/libs/Allay-Server-$version-all.jar")
}

tasks.installShadowDist {
    enabled = false
}

tasks.jar {
    manifest {
        attributes["Multi-Release"] = "true"
    }
}

tasks.shadowJar {
    transform(Log4j2PluginsCacheFileTransformer())
}