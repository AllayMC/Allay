group = "org.allaymc"
description = "data"
version = "0.0.1"

dependencies {
    implementation(project(":Allay-Server"))
}

tasks.processResources {
    from("${rootProject.projectDir}/Allay-Data/resources")
    include("unpacked/**")
}