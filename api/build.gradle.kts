/**
 * The current version of allay-api
 */
version = "0.4.0"

dependencies {
    api(libs.network) {
        exclude(group = "org.cloudburstmc", module = "nbt") // Use allaymc's nbt library
        exclude(group = "org.cloudburstmc.fastutil.commons")
        exclude(group = "org.cloudburstmc.fastutil.maps")
    }
    api(libs.stateupdater)
    api(libs.nbt)
    api(libs.slf4j.api)
    api(libs.bundles.fastutil)
    api(libs.guava)
    api(libs.gson)
    api(libs.annotations)
    api(libs.commonslang3)
    api(libs.semver4j)
    api(libs.joml) {
        // NOTICE: this is an accident that joml marked kotlin-stdlib as its dependency
        // in the recent version, see https://github.com/JOML-CI/JOML/pull/357 for more
        // information. And this is a quick workaround that we just exclude kotlin-stdlib
        // TODO: remove this workaround when joml fixed this issue
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    api(libs.joml.primitives) {
        // Use the latest version of joml, as joml primitives
        // lib uses an old version of joml for no reason
        exclude(group = "org.joml", module = "joml")
    }
    api(libs.okaeri.configs.yaml.snakeyaml) {
        exclude(group = "org.yaml", module = "snakeyaml") // Use the latest version
    }
    api(libs.snakeyaml)
    api(libs.caffeine)
}
