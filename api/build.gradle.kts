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
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    api(libs.joml.primitives) {
        // Use our latest version of joml
        exclude(group = "org.joml", module = "joml")
    }
    api(libs.snakeyaml)
    api(libs.semver4j)
}

tasks.withType<Javadoc>().configureEach {
    enabled = true
    isFailOnError = false

    (options as StandardJavadocDocletOptions).apply {
        encoding = "UTF-8"
        addStringOption("Xdoclint:none", "-quiet")
    }
}
