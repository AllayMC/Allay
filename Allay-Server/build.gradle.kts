plugins {
    id("application")
}

application {
    mainClass.set("cn.allay.Allay")
}

dependencies {
    implementation(project(":Allay-API"))
    implementation(project(":Allay-Level-Loader"))
    api(libs.network)
    api(libs.fastutil)
    api(libs.guava)
    api(libs.gson)
    api(libs.snakeyaml)
    api(libs.bundles.terminal)
    api(libs.bundles.log4j)
}

description = "Allay-Server"
