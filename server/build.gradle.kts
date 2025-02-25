import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer
import java.io.ByteArrayOutputStream

/**
 * The current version of allay-server
 *
 * This is not the same as the version of allay-api.
 * Please note that they are two things.
 */
version = "0.1.4" + if (rootProject.ext.get("isDevBuild") as Boolean) "-dev" else ""

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
    implementation(libs.bundles.leveldb)
    implementation(libs.netty.epoll)
    implementation(libs.netty.kqueue)
    implementation(libs.fastreflect)
    implementation(libs.oshi)
    implementation(libs.flatlaf)
    implementation(libs.formsrt)
    implementation(libs.bundles.rng)
    implementation(libs.sentry)
    implementation(libs.jctools)

    testImplementation(libs.bundles.junit)
    testRuntimeOnly(libs.junit.platform.launcher)
    testAnnotationProcessor(libs.lombok)
}

gitProperties {
    gitPropertiesName = "git.properties"
    gitPropertiesResourceDir.set(file("${rootProject.projectDir}/data/resources"))
    gitProperties {
        customProperty("git.build.is_dev_build", rootProject.ext.get("isDevBuild") as Boolean)
        /**
         * The version of allay-api.
         *
         * There are two versions in git.properties:
         * - version: The version of allay-server
         * - api_version: The version of allay-api
         */
        customProperty("git.build.api_version", project(":api").version)
    }
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
    jarFile = file(
        "build/libs/${getShadedJarName()}"
    )
}

tasks.shadowJar {
    transform(Log4j2PluginsCacheFileTransformer())
    archiveFileName = getShadedJarName()
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.required = false
    }
    additionalClassDirs(file("${rootProject.projectDir}/api/build/classes/java/main"))
    additionalSourceDirs(file("${rootProject.projectDir}/api/src/main/java"))
}

tasks.test {
    useJUnitPlatform()
    workingDir = file("${rootProject.projectDir}/.test/")
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

fun getShadedJarName(): String {
    return "allay-server-${version}-${getShortGitHash()}-shaded.jar"
}

fun getShortGitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = mutableListOf("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}