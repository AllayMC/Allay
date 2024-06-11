plugins {
    id("buildlogic.common")
}


group = "org.allaymc"
description = "example-plugin"
version = "1.0.0"

dependencies {
    compileOnly(project(":Allay-API"))
}

tasks.shadowJar {
    destinationDirectory = File("${rootProject.projectDir}/.run/plugins")
}