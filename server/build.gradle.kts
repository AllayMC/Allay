import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer
import org.gradle.kotlin.dsl.support.serviceOf
import java.io.ByteArrayOutputStream

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
    implementation(libs.bundles.logging)
    implementation(libs.bundles.leveldb)
    implementation(libs.bundles.rng)
    implementation(libs.bundles.fastutil)
    implementation(libs.bundles.stateupdater)
    implementation(libs.semver4j)
    implementation(libs.commonslang3)
    implementation(libs.commonsio)
    implementation(libs.mcterminal)
    implementation(libs.jline.reader)
    implementation(libs.disruptor)
    implementation(libs.netty.epoll)
    implementation(libs.netty.kqueue)
    implementation(libs.fastreflect)
    implementation(libs.oshi)
    implementation(libs.flatlaf)
    implementation(libs.formsrt)
    implementation(libs.sentry)
    implementation(libs.jctools)
    implementation(libs.caffeine)
    implementation(libs.bundles.protocol) {
        exclude(group = "org.cloudburstmc", module = "nbt") // Use allaymc's nbt library
        exclude(group = "org.cloudburstmc.fastutil.commons")
        exclude(group = "org.cloudburstmc.fastutil.maps")
    }
    implementation(libs.okaeri.configs.yaml.snakeyaml) {
        exclude(group = "org.yaml", module = "snakeyaml") // Use the latest version
    }
    implementation(libs.bstats)

    testImplementation(libs.bundles.junit)
    testRuntimeOnly(libs.junit.platform.launcher)
}

gitProperties {
    dotGitDirectory = project.rootProject.layout.projectDirectory.dir(".git")
    gitPropertiesResourceDir.set(file("${rootProject.projectDir}/data/resources"))
    gitProperties {
        customProperty("git.build.is_dev_build", rootProject.property("allay.is-dev-build").toString().toBoolean())
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

jacoco {
    reportsDirectory = layout.buildDirectory.dir("${rootProject.projectDir}/.jacoco")
}

tasks {
    processResources {
        dependsOn("generateGitProperties")
        // input directory
        from("${rootProject.projectDir}/data/resources")
        // exclude unpacked folder and block_palette.nbt
        exclude("unpacked")
    }

    sourcesJar {
        dependsOn("generateGitProperties")
    }

    shadowJar {
        archiveFileName = getShadedJarName()
        duplicatesStrategy = DuplicatesStrategy.INCLUDE

        transform<Log4j2PluginsCacheFileTransformer>()
        mergeServiceFiles()

        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/NOTICE")
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
    }

    runShadow {
        workingDir = file("${rootProject.projectDir}/.run/")
    }

    jacocoTestReport {
        reports {
            xml.required = true
            html.required = false
        }
        additionalClassDirs(file("${rootProject.projectDir}/api/build/classes/java/main"))
        additionalSourceDirs(file("${rootProject.projectDir}/api/src/main/java"))
    }

    test {
        useJUnitPlatform()
        workingDir = file("${rootProject.projectDir}/.test/")
    }

    register("cleanWorkingDir") {
        description = "Clean all files in `.run` directory except `Allay.run.xml` file"
        group = "application"
        doLast {
            rootProject.rootDir.resolve(".run").listFiles { f -> !f.name.equals("Allay.run.xml") }?.forEach {
                delete(it)
            }
        }
    }
}

fun getShadedJarName(): String {
    return "allay-server-${version}-${getShortGitHash()}-shaded.jar"
}

fun getShortGitHash(): String {
    val execOperations = project.serviceOf<ExecOperations>()

    val stdout = ByteArrayOutputStream()
    execOperations.exec {
        commandLine = mutableListOf("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}