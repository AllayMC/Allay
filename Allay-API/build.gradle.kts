plugins {
    `java-library`
}

group = "cn.allay"
description = "api"
version = "0.0.1"

java {
    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(libs.network) {
        exclude(group = "org.cloudburstmc", module = "nbt") // use allay nbt
        exclude(group = "com.nukkitx.fastutil") // use it.unimi.dsi.fastutil
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
    api(libs.okaeri.configs.yaml.snakeyaml)
    implementation(libs.libdeflate)
    implementation(libs.bundles.asm)
}

tasks.processResources {
    // input directory
    from("${rootProject.projectDir}/Allay-Data/resources")
    include("mappings")
}