plugins {
    id("application")
}

application {
    mainClass.set("cn.allay.Allay")
}

dependencies {
    api(project(":Allay-Api"))
    api(libs.bytebuddy)
    api(libs.network)
    api(libs.fastutil)
    api(libs.guava)
    api(libs.gson)
    api(libs.snakeyaml)
    api(libs.bundles.terminal)
    api(libs.bundles.log4j)
}

description = "Allay-Server"
