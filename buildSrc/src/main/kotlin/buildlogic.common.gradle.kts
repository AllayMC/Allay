import java.nio.charset.StandardCharsets

plugins {
    // Apply the java Plugin to add support for Java.
    java
    idea
    `maven-publish`
    id("com.github.johnrengelman.shadow")
}

java {
    withJavadocJar()
    withSourcesJar()
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenLocal()
    mavenCentral()
    maven("https://www.jitpack.io/")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    // NOTICE: libs.versions.toml cannot be used in public plugin scripts
    compileOnly("org.projectlombok:lombok:1.18.32")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")

    testCompileOnly("org.projectlombok:lombok:1.18.32")

    annotationProcessor("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.build {
    //control the task group is  "alpha build"
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
}

// disable assemble
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