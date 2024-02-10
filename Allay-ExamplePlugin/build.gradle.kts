plugins {
    `java-library`
}

group = "org.allaymc"
description = "example-plugin"
version = "0.0.1"

dependencies {
    compileOnly(project(":Allay-API"))

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    destinationDirectory = File("${rootProject.projectDir}/run/plugins")
}