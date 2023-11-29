plugins {
    `java-library`
}

group = "org.allaymc"
description = "api"
version = "0.0.1"

java {
    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(libs.network) {
        exclude(group = "org.cloudburstmc", module = "nbt") // use allay nbt
        exclude(group = "org.cloudburstmc.fastutil.commons")
        exclude(group = "org.cloudburstmc.fastutil.maps")
    }
    api(libs.blockstateupdater) {
        exclude(group = "org.cloudburstmc", module = "nbt")
        exclude(group = "com.fasterxml.jackson.core", module = "jackson-databind")//use my latest version
    }
    api(libs.slf4j.api)
    api(libs.nbt)
    api(libs.fastutil)
    api(libs.guava)
    api(libs.gson)
    api(libs.snakeyaml)
    api(libs.annotations)
    api(libs.commonsio)
    api(libs.commonslang3)
    api(libs.joml)
    api(libs.joml.primitives)
    api(libs.okaeri.configs.yaml.snakeyaml) {
        exclude(group = "org.yaml", module = "snakeyaml")//use my latest version
    }
    api(libs.bundles.commands)
    implementation(libs.libdeflate)
}