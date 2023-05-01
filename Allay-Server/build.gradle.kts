plugins {
    id("application")
}

application {
    mainClass.set("cn.allay.Allay")
}

dependencies {
    implementation(project(":Allay-API"))
    implementation(libs.bytebuddy)
}

description = "Allay-Server"

sourceSets {
    test {
        java {
            srcDirs("${rootProject.projectDir}/Test")
        }
    }
}
