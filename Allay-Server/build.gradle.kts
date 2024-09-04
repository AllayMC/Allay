import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

plugins {
    id("jacoco")
    id("application")
    id("maven-publish")
    alias(libs.plugins.jmh)
    alias(libs.plugins.gitproperties)
    alias(libs.plugins.shadow)
}

version = "1.0.0"

application {
    mainClass.set("org.allaymc.server.Allay")
}

dependencies {
    api(project(":Allay-API"))
    implementation(libs.commonsio)
    implementation(libs.mcterminal)
    implementation(libs.bundles.logging)
    implementation(libs.disruptor)
    implementation(libs.bytebuddy)
    implementation(libs.libdeflate)
    implementation(libs.leveldbjni)
    implementation(libs.netty.epoll)
    implementation(libs.netty.kqueue)
    implementation(libs.jegenerator)
    implementation(libs.fastreflect)
    implementation(libs.reflections)
    implementation(libs.polyglot)
    implementation(libs.js)
    implementation(libs.bundles.graalvm)
    implementation(libs.oshi)
    implementation(libs.flatlaf)
    implementation(libs.formsrt)

    testImplementation(libs.bundles.junit)
    testAnnotationProcessor(libs.lombok)
}

gitProperties {
    gitPropertiesName = "git.properties"
    gitPropertiesResourceDir.set(file("${rootProject.projectDir}/Allay-Data/resources"))
}

tasks.processResources {
    dependsOn("generateGitProperties")
    // input directory
    from("${rootProject.projectDir}/Allay-Data/resources")
    // exclude unpacked folder and block palette.nbt
    exclude("${rootProject.projectDir}/unpacked/**")
}

tasks.runShadow {
    workingDir = file("${rootProject.projectDir}/.run/")
    this.jarFile = file("build/libs/Allay-Server-$version-all.jar")
}

tasks.shadowJar {
    transform(Log4j2PluginsCacheFileTransformer())
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.required = false
    }
    additionalClassDirs(file("${rootProject.projectDir}/Allay-API/build/classes/java/main"))
    additionalSourceDirs(file("${rootProject.projectDir}/Allay-API/src/main/java"))
}

jacoco {
    reportsDirectory = layout.buildDirectory.dir("${rootProject.projectDir}/.jacoco")
}

tasks.create("cleanWorkingDir") {
    description = "Clean all files in `.run` directory except `Allay.run.xml` file"
    group = "application"
    rootProject.rootDir.resolve(".run").listFiles { f -> !f.name.equals("Allay.run.xml") }?.forEach {
        delete(it)
    }
}