buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://repo.maven.apache.org/maven2/")
        }
    }
    dependencies {
        classpath("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

group = "cn.allay"
description = "The next generation minecraft server software"

plugins {
    `kotlin-dsl`
    idea
}

//Do not build this root project, this is only used as a control submodule
tasks.forEach {
    it.enabled = false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    java.sourceCompatibility = JavaVersion.VERSION_19
    java.targetCompatibility = JavaVersion.VERSION_19

    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url = uri("https://repo.opencollab.dev/maven-releases/")
        }
        maven {
            url = uri("https://repo.opencollab.dev/maven-snapshots/")
        }
        maven {
            url = uri("https://repo.maven.apache.org/maven2/")
        }
        maven {
            url = uri("https://www.jitpack.io/")
        }
    }

    dependencies {
        testImplementation(rootProject.libs.bundles.junit)

        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)

        testCompileOnly(rootProject.libs.lombok)
        testAnnotationProcessor(rootProject.libs.lombok)
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }

    rootProject.idea {
        module {
            isDownloadSources = false
            isDownloadJavadoc = true
        }
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()
        jvmArgs = listOf("--enable-preview")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("--enable-preview")
    }

    tasks.withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<Javadoc> {
        options.encoding = "UTF-8"
        //Suppress some meaningless warnings
        options {
            (this as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
        }
    }
}
