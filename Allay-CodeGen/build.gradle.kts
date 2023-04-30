plugins {
    id("java")
}

description = "Allay-CodeGen"

dependencies {
    api(project(":Allay-API"))
    implementation(libs.javapoet)
}