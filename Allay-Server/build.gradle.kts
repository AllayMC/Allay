plugins {
    id("application")
}

application {
    mainClass.set("cn.allay.Allay")
}

dependencies {
    implementation(project(":Allay-API"))
    implementation(project(":Allay-Level-Loader"))
    implementation(libs.bytebuddy)
}

description = "Allay-Server"
