plugins {
    id("buildlogic.common")
}

group = "org.allaymc"
description = "data"
version = "1.0.0"

dependencies {
    implementation(project(":Allay-Server"))
}

tasks.processResources {
    from("${rootProject.projectDir}/Allay-Data/resources")
    include("unpacked/**")
}