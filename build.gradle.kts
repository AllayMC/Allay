import java.nio.charset.StandardCharsets

plugins {
    java
    idea
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.allaymc"
description = "The next generation minecraft server software"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://www.jitpack.io/")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = false
    }
}

// Do not build this root project, this is only used as a control submodule
tasks.forEach {
    it.enabled = false
}

// Enable gradle wrapper update task
tasks.wrapper {
    enabled = true
    gradleVersion = "8.6"
}

tasks.prepareKotlinBuildScriptModel {
    enabled = true
}

tasks.build {
    group = "alpha build"
}

// The build of the root module does not need to write logic,
// and the build of the same name of all submodules will be automatically called
tasks.register<DefaultTask>("fastBuild") {
    group = "alpha build"
}

tasks.register<DefaultTask>("buildForGithubAction") {
    group = "alpha build"
}

tasks.clean {
    group = "alpha build"
    enabled = true
    File(".run").listFiles { f -> !f.name.equals("Allay.run.xml") }?.forEach {
        delete(it)
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    java.sourceCompatibility = JavaVersion.VERSION_21
    java.targetCompatibility = JavaVersion.VERSION_21

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://www.jitpack.io/")
        maven("https://repo.opencollab.dev/maven-releases/")
        maven("https://repo.opencollab.dev/maven-snapshots/")
        maven("https://storehouse.okaeri.eu/repository/maven-public/")
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
        group = "alpha build"
        dependsOn("shadowJar")
    }

    tasks.register<DefaultTask>("fastBuild") {
        dependsOn(tasks.build)
        group = "alpha build"
        tasks["javadoc"].enabled = false
        tasks["javadocJar"].enabled = false
        tasks["sourcesJar"].enabled = false
        tasks["compileTestJava"].enabled = false
        tasks["processTestResources"].enabled = false
        tasks["testClasses"].enabled = false
        tasks["test"].enabled = false
        tasks["check"].enabled = false
    }

    tasks.register<DefaultTask>("buildForGithubAction") {
        dependsOn(tasks.build)
        group = "alpha build"
        tasks["javadoc"].enabled = false
        tasks["javadocJar"].enabled = false
    }

    tasks.clean {
        group = "alpha build"
        enabled = true
    }

    // disable
    tasks.assemble {
        enabled = false
    }

    tasks.test {
        useJUnitPlatform()
        workingDir = File("${rootProject.projectDir}/.run/")
    }

    tasks.withType<JavaCompile> {
        options.encoding = StandardCharsets.UTF_8.name()
    }

    tasks.withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<Javadoc> {
        options.encoding = StandardCharsets.UTF_8.name()
        val javadocOptions = options as CoreJavadocOptions
        javadocOptions.addStringOption(
                "source",
                java.sourceCompatibility.toString()
        )
        // Suppress some meaningless warnings
        javadocOptions.addStringOption("Xdoclint:none", "-quiet")
    }
}