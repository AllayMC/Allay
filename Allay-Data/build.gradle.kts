plugins {
    id("buildlogic.common")
    kotlin("jvm")
}

group = "org.allaymc"
description = "data"
version = "1.0.0"

dependencies {
    implementation("com.nimbusds:nimbus-jose-jwt:9.10.1")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation(libs.nbt)
    implementation(libs.network) {
        exclude(group = "org.cloudburstmc", module = "nbt") // use allay nbt
    }
    implementation(kotlin("stdlib-jdk8"))
}

tasks.processResources {
    from("${rootProject.projectDir}/Allay-Data/resources")
    include("unpacked/**")
}

kotlin {
    jvmToolchain(21)
}