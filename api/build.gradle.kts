dependencies {
    api(libs.nbt)
    api(libs.slf4j.api)
    api(libs.guava)
    api(libs.gson)
    api(libs.annotations)
    api(libs.joml) {
        // NOTICE: this is an accident that joml marked kotlin-stdlib as its dependency
        // in the recent version, see https://github.com/JOML-CI/JOML/pull/357 for more
        // information. And this is a quick workaround that we just exclude kotlin-stdlib
        // TODO: remove this workaround when joml release 1.10.9
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
        // Use the latest version of joml, as joml primitives
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    api(libs.joml.primitives) {
        // lib uses an old version of joml for no reason
        exclude(group = "org.joml", module = "joml")
    }
    api(libs.snakeyaml)
}
