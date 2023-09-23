repositories {
    mavenCentral()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
    mavenLocal()
}

group = "cn.allay"
description = "The next generation minecraft server software"

plugins {
    id("org.gradle.kotlin.kotlin-dsl") version "4.1.2"
    idea
    id("org.jetbrains.kotlin.jvm") version "1.9.20-Beta2"
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

//Do not build this root project, this is only used as a control submodule
tasks.forEach {
    it.enabled = false
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

//Enable gradle wrapper update task
tasks.wrapper {
    enabled = true
    gradleVersion = "8.4-rc-1"
}

tasks.clean {
    enabled = true
    delete("logs", "cache", "output")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    java.sourceCompatibility = JavaVersion.VERSION_21
    java.targetCompatibility = JavaVersion.VERSION_21

    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.opencollab.dev/maven-releases/")
        }
        maven {
            url = uri("https://repo.opencollab.dev/maven-snapshots/")
        }
        maven {
            url = uri("https://www.jitpack.io/")
        }
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

    //disable
    tasks.assemble {
        group = ""
        enabled = false
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<Javadoc> {
        options.encoding = "UTF-8"
        val javadocOptions = options as CoreJavadocOptions
        javadocOptions.addStringOption("source", "21")
        //Suppress some meaningless warnings
        javadocOptions.addStringOption("Xdoclint:none", "-quiet")
    }
}
