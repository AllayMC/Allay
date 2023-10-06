import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

plugins {
    id("java-library")
    id("application")
    id("me.champeau.jmh") version ("0.7.1")
    id("com.gorylenko.gradle-git-properties") version ("2.4.1")
}

description = "server"
group = "cn.allay"
version = "0.0.1"

application {
    mainClass.set("cn.allay.server.Allay")
}

dependencies {
    api(project(":Allay-API"))
    implementation(libs.bundles.terminal)
    implementation(libs.progressbar)
    implementation(libs.bundles.logging)
    implementation(libs.bytebuddy)
    implementation(libs.libdeflate)
    implementation(libs.rocksdb)
    implementation(libs.netty.epoll)
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
//disable
tasks.distTar {
    group = ""
    enabled = false
}
//disable
tasks.distZip {
    group = ""
    enabled = false
}

tasks.named<Delete>("clean") {
    delete("logs", "caches")
}

tasks.sourcesJar {
    dependsOn("generateGitProperties")
}

tasks.jmh {
    //Add the executed test case
    includes.add("AABBTreeJMHTest")
    //includes.add("BlockStateUpdateJMHTest")
    //includes.add("ChunkJMHTest")
    //includes.add("ThroughList2Array")
}

//Hiding this task should use runShadow
tasks.named("run") {
    group = ""
}

tasks.runShadow {
    this.jarFile = file("build/libs/Allay-Server-0.0.1-all.jar")
}

tasks.installShadowDist {
    enabled = false
}

tasks.shadowJar {
    transform(Log4j2PluginsCacheFileTransformer())
}