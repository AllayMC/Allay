plugins {
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
}

group = "cn.allay"
description = "mapping"
version = "0.0.1"
val javaMinecraftVersion = "1.20.1"

dependencies {
    implementation(project(":Allay-Server"))
}

minecraft {
    // https://github.com/SpongePowered/Sponge/blob/3cb480a347a33a424797c0e8f36b91cd1437d21d/build.gradle.kts
    version(javaMinecraftVersion)
    platform(org.spongepowered.gradle.vanilla.repository.MinecraftPlatform.CLIENT)
    accessWideners(sourceSets.getByName("main").resources.filter { it.name.endsWith(".accesswidener") })
}