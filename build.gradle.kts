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
    java
    application
}

//不构建这个根项目,这个只作为控制子模块
tasks.forEach {
    it.enabled = false
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    java.sourceCompatibility = JavaVersion.VERSION_19

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
    }

    dependencies {
        api(rootProject.libs.annotations)
        testImplementation(rootProject.libs.bundles.junit)

        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)

        testCompileOnly(rootProject.libs.lombok)
        testAnnotationProcessor(rootProject.libs.lombok)
    }

    sourceSets {
        main {
            resources {
                srcDirs("src/main/resources", "${rootProject.projectDir}/Data")
                exclude("${rootProject.projectDir}/Data/unpacked")
                exclude("${rootProject.projectDir}/Data/bedrock-samples")
            }
        }
        test {
            resources {
                srcDirs("src/test/resources", "${rootProject.projectDir}/Data")
                exclude("${rootProject.projectDir}/Data/unpacked")
                exclude("${rootProject.projectDir}/Data/bedrock-samples")
            }
        }
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

    tasks.withType<JavaCompile>() {
        options.encoding = "UTF-8"
        options.compilerArgs.add("--enable-preview")
    }

    tasks.withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<Javadoc>() {
        options.encoding = "UTF-8"
    }
}
