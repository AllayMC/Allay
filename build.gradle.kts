plugins {
    id("java-library")
    id("maven-publish")
}

val publishedModules = listOf("Allay-API", "Allay-Server")

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    group = "org.allaymc"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    repositories {
        mavenCentral()
        maven("https://repo.opencollab.dev/maven-releases/")
        maven("https://repo.opencollab.dev/maven-snapshots/")
        maven("https://storehouse.okaeri.eu/repository/maven-public/")
        maven("https://www.jitpack.io/")
    }

    dependencies {
        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)

        testCompileOnly(rootProject.libs.lombok)
        testAnnotationProcessor(rootProject.libs.lombok)
    }

    if (publishedModules.contains(name)) {
        publishing {
            repositories {
                // Jitpack requires us to publish artifacts to local maven repo
                mavenLocal()
            }

            publications {
                create<MavenPublication>("maven") {
                    from(components["java"])
                    pom {
                        inceptionYear.set("2023")
                        packaging = "jar"
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
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
            configureEach {
                options.isFork = true
            }
        }

        withType<Test> {
            useJUnitPlatform()
            workingDir = file("${rootProject.projectDir}/.run/")
        }

        withType<Copy> {
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }
    }
}