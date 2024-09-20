version = "1.0.0"

dependencies {
    implementation(project(":server"))
}

tasks.processResources {
    from("${rootProject.projectDir}/data/resources")
    include("${rootProject.projectDir}/data/resources/unpacked/**")
}

tasks.publish {
    enabled = false
}