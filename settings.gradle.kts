pluginManagement {
    repositories {
        maven("https://repo.spongepowered.org/repository/maven-public/") {
            name = "sponge"
        }
    }
}

include(":Allay-API")
include(":Allay-Server")
include(":Allay-CodeGen")
include(":Allay-Data")
