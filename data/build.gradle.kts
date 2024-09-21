version = "1.0.0"

dependencies {
    implementation(project(":server"))
}

tasks.publish {
    enabled = false
}