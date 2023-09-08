plugins {
    id("java-library")
}

description = "api"
group = "cn.allay"
version = "0.0.1"

dependencies {
    api(libs.network) {
        //use allay nbt
        exclude(group = "org.cloudburstmc", module = "nbt")
        //use it.unimi.dsi.fastutil
        exclude(group = "com.nukkitx.fastutil")
    }
    api(libs.slf4j.api)
    api(libs.disruptor)
    api(libs.nbt)
    api(libs.fastutil)
    api(libs.guava)
    api(libs.gson)
    api(libs.snakeyaml)
    api(libs.annotations)
    api(libs.reflections)
    api(libs.caffeine)
    api(libs.commonsio)
    api(libs.joml)
    api(libs.joml.primitives)
    implementation(libs.libdeflate)
}

tasks.processResources {
    // input directory
    from("${rootProject.projectDir}/Allay-Data/resources")
    include("mappings")
}