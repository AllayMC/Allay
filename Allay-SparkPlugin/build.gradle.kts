plugins {
    `java-library`
    id("com.google.protobuf") version "0.9.4"
}

group = "org.allaymc"
description = "spark is a performance profiling plugin/mod for Minecraft clients, servers and proxies."
version = "1.0.0"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    compileOnly(project(":Allay-API"))

    implementation("me.lucko:spark-api:0.1-SNAPSHOT")
    implementation(files("libs/spark-common-1.10-SNAPSHOT.jar"))
    implementation("net.kyori:adventure-text-serializer-legacy:4.13.1")

    implementation("com.github.jvm-profiling-tools:async-profiler:v2.8.3")
    implementation("org.ow2.asm:asm:9.6")
    implementation("net.bytebuddy:byte-buddy-agent:1.11.0")
    implementation("com.google.protobuf:protobuf-javalite:3.25.2")
    implementation("me.lucko:bytesocks-java-client:1.0-20230828.145440-5") {
        exclude(module = "slf4j-api")
    }
    implementation("net.kyori:adventure-text-feature-pagination:4.0.0-SNAPSHOT") {
        exclude(module = "adventure-api")
    }
}

tasks.processResources {
    filesMatching("plugin.json") {
        expand(
            "description" to project.description,
            "version" to project.version
        )
    }
}

tasks.shadowJar {
    archiveFileName = "spark-${project.version}-allay.jar"
    destinationDirectory = File("${rootProject.projectDir}/run/plugins")

    relocate("net.kyori.adventure", "org.allaymc.spark.lib.adventure")
    relocate("net.kyori.examinatio", "org.allaymc.spark.lib.adventure.examination")
    relocate("net.bytebuddy", "org.allaymc.spark.lib.bytebuddy")
    relocate("com.google.protobuf", "org.allaymc.spark.lib.protobuf")
    relocate("org.objectweb.asm", "org.allaymc.spark.lib.asm")
    relocate("one.profiler", "org.allaymc.spark.lib.asyncprofiler")
    relocate("me.lucko.bytesocks.client", "org.allaymc.spark.lib.bytesocks")
    relocate("org.java_websocket", "org.allaymc.spark.lib.bytesocks.ws")

    exclude("module-info.class")
    exclude("META-INF/maven/**")
    exclude("META-INF/proguard/**")
}
