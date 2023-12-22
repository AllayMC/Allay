import org.gradle.tooling.model.java.JavaRuntime

plugins {
    java
    idea
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.allaymc"
description = "The next generation minecraft server software"

repositories {
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    mavenLocal()
}

// Do not build this root project, this is only used as a control submodule
tasks.forEach {
    it.enabled = false
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

// Enable gradle wrapper update task
tasks.wrapper {
    enabled = true
    gradleVersion = GradleVersion.current().version
}

tasks.prepareKotlinBuildScriptModel {
    enabled = true
}

tasks.withType<Delete> {
    enabled = true
    delete("logs", "caches", "output", "server-settings.yml")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    java.sourceCompatibility = JavaVersion.VERSION_21
    java.targetCompatibility = JavaVersion.VERSION_21

    repositories {
        mavenCentral()
        maven("https://repo.opencollab.dev/maven-releases/")
        maven("https://repo.opencollab.dev/maven-snapshots/")
        maven("https://www.jitpack.io/")
        maven("https://storehouse.okaeri.eu/repository/maven-public/")
        mavenLocal()
    }

    dependencies {
        testImplementation(rootProject.libs.bundles.test)

        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)

        testCompileOnly(rootProject.libs.lombok)
        testAnnotationProcessor(rootProject.libs.lombok)
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }

    tasks.build {
        dependsOn("shadowJar")
    }

    // disable
    tasks.assemble {
        group = ""
        enabled = false
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "utf-8"
    }

    tasks.withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<Javadoc> {
        options.encoding = "utf-8"
        val javadocOptions = options as CoreJavadocOptions
        javadocOptions.addStringOption(
            "source",
            java.sourceCompatibility.toString()
        )
        // Suppress some meaningless warnings
        javadocOptions.addStringOption("Xdoclint:none", "-quiet")
    }
}
