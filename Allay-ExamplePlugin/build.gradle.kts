plugins {
    `java-library`
}

group = "org.allaymc"
description = "example-plugin"
version = "0.0.1"

dependencies {
    compileOnly(project(":Allay-API"))
}

tasks.buildForGithubAction {
    enabled = false
}

tasks.shadowJar {
    destinationDirectory = File("${rootProject.projectDir}/.run/plugins")
}