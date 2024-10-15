import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

version = SimpleDateFormat("yyyy.MM.dd.HHmmss").format(Date())

plugins {
    id("jacoco")
    id("application")
    alias(libs.plugins.jmh)
    alias(libs.plugins.gitproperties)
    alias(libs.plugins.shadow)
}

application {
    mainClass.set("org.allaymc.server.Allay")
}

dependencies {
    api(project(":api"))
    implementation(libs.commonsio)
    implementation(libs.mcterminal)
    implementation(libs.bundles.logging)
    implementation(libs.disruptor)
    implementation(libs.bytebuddy)
    implementation(libs.libdeflate)
    implementation(libs.leveldbjni)
    implementation(libs.netty.epoll)
    implementation(libs.netty.kqueue)
    implementation(libs.fastreflect)
    implementation(libs.reflections)
    implementation(libs.oshi)
    implementation(libs.flatlaf)
    implementation(libs.formsrt)
    implementation(libs.rng.simple)
    implementation(libs.rng.sampling)

    testImplementation(libs.bundles.junit)
    testAnnotationProcessor(libs.lombok)
}

gitProperties {
    gitPropertiesName = "git.properties"
    gitPropertiesResourceDir.set(file("${rootProject.projectDir}/data/resources"))
}

tasks.processResources {
    dependsOn("generateGitProperties")
    // input directory
    from("${rootProject.projectDir}/data/resources")
    // exclude unpacked folder and block_palette.nbt
    exclude("unpacked")
}

tasks.sourcesJar {
    dependsOn("generateGitProperties")
}

tasks.runShadow {
    workingDir = file("${rootProject.projectDir}/.run/")
    jarFile = file("build/libs/allay-server-${version}-${getShortGitHash()}-shaded.jar")
}

tasks.shadowJar {
    transform(Log4j2PluginsCacheFileTransformer())
    archiveFileName = "allay-server-${version}-${getShortGitHash()}-shaded.jar"
}

fun getShortGitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = mutableListOf("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.required = false
    }
    additionalClassDirs(file("${rootProject.projectDir}/api/build/classes/java/main"))
    additionalSourceDirs(file("${rootProject.projectDir}/api/src/main/java"))
}

jacoco {
    reportsDirectory = layout.buildDirectory.dir("${rootProject.projectDir}/.jacoco")
}

tasks.create("cleanWorkingDir") {
    description = "Clean all files in `.run` directory except `Allay.run.xml` file"
    group = "application"
    doLast {
        rootProject.rootDir.resolve(".run").listFiles { f -> !f.name.equals("Allay.run.xml") }?.forEach {
            delete(it)
        }
    }
}

tasks.create("cleanCachesInWorkingDir") {
    description = "Clean caches dir in `.run` directory"
    group = "application"
    doLast {
        rootProject.rootDir.resolve(".run").listFiles { f -> f.name.equals("caches") }?.forEach {
            delete(it)
        }
    }
}