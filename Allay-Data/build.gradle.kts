version = "1.0.0"

dependencies {
    implementation(project(":Allay-Server"))
}

tasks.processResources {
    from("${rootProject.projectDir}/Allay-Data/resources")
    include("${rootProject.projectDir}/Allay-Data/resources/unpacked/**")
}