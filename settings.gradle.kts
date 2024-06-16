pluginManagement {
    plugins {
        kotlin("jvm") version "2.0.0"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "Allay"

// include multi modules
include(":Allay-API")
include(":Allay-Server")
include(":Allay-CodeGen")
include(":Allay-Data")
include(":Allay-ExamplePlugin")
include(":Allay-SparkPlugin")
