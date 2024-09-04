plugins {
    alias(libs.plugins.shadow)
}

version = "1.0.0"

dependencies {
    compileOnly(project(":Allay-API"))
}