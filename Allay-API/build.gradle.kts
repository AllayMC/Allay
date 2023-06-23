plugins {
    id("java-library")
}

description = "Allay-API"

dependencies {
    api(libs.bundles.log4j.api)
    api(libs.network) {
        //use allay nbt
        exclude(group = "org.cloudburstmc", module = "nbt")
        //use it.unimi.dsi.fastutil
        exclude(group = "com.nukkitx.fastutil")
    }
    api(libs.nbt)
    api(libs.fastutil)
    api(libs.guava)
    api(libs.gson)
    api(libs.snakeyaml)
    api(libs.annotations)
    api(libs.reflections)
    //TODO: No idea why this not work
//    api(libs.fasterxml)
    api("com.fasterxml.jackson.core", "jackson-core", "2.14.2")
    api("com.fasterxml.jackson.core", "jackson-databind", "2.14.2")
    implementation(libs.libdeflate)
}
