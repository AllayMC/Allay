import com.vanniktech.maven.publish.MavenPublishBaseExtension

plugins {
    id("java-library")
    alias(libs.plugins.publish) apply false
}

subprojects {
    apply(plugin = "java-library")

    group = "org.allaymc.allay"

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
            configureEach {
                options.isFork = true
            }
        }

        withType<Copy> {
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    repositories {
        mavenCentral()
        maven("https://www.jetbrains.com/intellij-repository/releases/")
        maven("https://repo.opencollab.dev/maven-releases/")
        maven("https://repo.opencollab.dev/maven-snapshots/")
        maven("https://storehouse.okaeri.eu/repository/maven-public/")
    }

    dependencies {
        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)

        testCompileOnly(rootProject.libs.lombok)
        testAnnotationProcessor(rootProject.libs.lombok)
    }

    if (project.name in listOf("api", "server")) {
        apply(plugin = "com.vanniktech.maven.publish")

        project.version = rootProject.property(project.name + ".version").toString() +
                if (rootProject.property("allay.is-dev-build").toString().toBoolean()) "-dev" else ""

        java {
            withSourcesJar()
        }

        // We already have sources jar, so no need to build Javadoc, which would cause a lot of warnings
        tasks.withType<Javadoc> {
            enabled = false
        }

        configure<MavenPublishBaseExtension> {
            publishToMavenCentral()
            signAllPublications()

            coordinates(
                project.group.toString(),
                project.name,
                project.version.toString().replace("-dev", "-SNAPSHOT")
            )

            pom {
                name.set(project.name)
                description.set("The next-generation Minecraft: Bedrock Edition server software aims to be reliable, fast and feature-rich.")
                inceptionYear.set("2023")
                url.set("https://github.com/AllayMC/Allay")

                scm {
                    connection.set("scm:git:git://github.com/AllayMC/Allay.git")
                    developerConnection.set("scm:git:ssh://github.com/AllayMC/Allay.git")
                    url.set("https://github.com/AllayMC/Allay")
                }

                licenses {
                    license {
                        name.set("LGPL 3.0")
                        url.set("https://www.gnu.org/licenses/lgpl-3.0.en.html")
                    }
                }

                developers {
                    developer {
                        name.set("AllayMC Team")
                        organization.set("AllayMC")
                        organizationUrl.set("https://github.com/AllayMC")
                    }
                }
            }
        }
    }
}