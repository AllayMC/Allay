plugins {
    `java-library`
}

group = "org.allaymc"
description = "example-plugin"
version = "0.0.1"

dependencies {
    implementation(project(":Allay-API"))

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}