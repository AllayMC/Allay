plugins {
    alias(libs.plugins.shadow)
}

version = "1.0.0"

dependencies {
    api(libs.network) {
        exclude(group = "org.cloudburstmc", module = "nbt") // Use allaymc's nbt library
        exclude(group = "org.cloudburstmc.fastutil.commons")
        exclude(group = "org.cloudburstmc.fastutil.maps")
    }
    api(libs.stateupdater)
    api(libs.nbt)
    api(libs.slf4j.api)
    api(libs.fastutil)
    api(libs.guava)
    api(libs.gson)
    api(libs.annotations)
    api(libs.commonslang3)
    api(libs.joml)
    api(libs.joml.primitives)
    api(libs.okaeri.configs.yaml.snakeyaml) {
        exclude(group = "org.yaml", module = "snakeyaml") // Use the latest version
    }
    api(libs.snakeyaml)
    api(libs.caffeine)
}

tasks.shadowJar {
    archiveClassifier = "shaded"
}