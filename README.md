<!-- PROJECT LOGO -->
<br/>
<div align="center">

<a href="https://github.com/AllayMC/Allay">
    <img src="docs/assets/logo/allay-chan-640x.png" alt="Logo" width="200" height="200">
</a>
<h3 align="center">Allay</h3>

The next-generation Minecraft: Bedrock Edition server software

<a href="https://github.com/AllayMC/Allay/actions"><img src="https://github.com/AllayMC/Allay/actions/workflows/gradle.yml/badge.svg" alt="Build"/></a>
<a href="https://docs.allaymc.org"><img src="https://readthedocs.org/projects/allaymc/badge/?version=latest" alt="Documentation Status"></a>
[![](https://jitpack.io/v/AllayMC/Allay.svg)](https://jitpack.io/#AllayMC/Allay)
[![codecov](https://codecov.io/gh/AllayMC/Allay/graph/badge.svg?token=EI8EDEKI51)](https://codecov.io/gh/AllayMC/Allay)
<a href="https://app.codacy.com/gh/AllayMC/Allay/dashboard"><img src="https://app.codacy.com/project/badge/Grade/30e264923da2425a8b777a84b4028334"></a>
<a href="https://discord.gg/ngkkE4hPTU"><img src="https://img.shields.io/discord/1147136608290750526?label=discord&color=7289DA&logo=discord" alt="Discord" /></a>
<a href="https://feedback.minecraft.net/hc/en-us/sections/360001186971-Release-Changelogs"><img src="https://img.shields.io/badge/minecraft-v1.21.30%20(Bedrock)-green" /></a>
<img src="https://img.shields.io/badge/protocol-729-blue">
</div>

## Introduction

[//]: # (Allay is the cutest software in the world!)

Allay is a third-party server software for Minecraft: Bedrock Edition written in Java with the goal of maintaining high scalability while maintaining high performance through a well-designed architecture.

> [!IMPORTANT]
> Please note that this project is still at a very early stage and has not yet been released officially, and a large number of interfaces may be added or removed without notice. Please do not use Allay in a production environment.
>
> You can check our roadmap for development progress.

## Features

Allay has many features that make it stand out from other server software, and here are some of them:

- **Cross-platform:** Allay is based on JVM, so it can run on most platforms that can run JVM.
- **High Performance:**
    - We fully understand the problems of Nukkit server under high load, Allay is a hundred times more performant than Nukkit in some aspects (eg: entity physics).
    - Thanks to the redesigned threading model, Allay takes full advantage of multicore CPUs, which means you don't need to intentionally use server that has high CPU frequency.
    - Allay is based on JDK 21, which theoretically gives it better performance.
- **Easy to Get Started:**
    - You can write plugins for Allay in Java, Kotlin, Scala (JVM based language) and script languages such as JavaScript, Python, etc.
    - We introduced GraalVM, which means you can write plugins in many languages and get the same performance as Java and the ability to interoperate seamlessly.
- **Highly Customizable:** Allay offers a large number of interfaces that can be used directly, and you can't do such thing in BDS.
- **Security:**
    - Allay has more checks on client packets than BDS, and theoretically does not have many of the pernicious vulnerabilities that BDS has.
    - Allay has network encryption enabled by default. In addition, Allay has a built-in resource packet encryption function that automatically encrypts resource packets sent to the client, preventing your data from leaking to a certain extent.
- **Lots of New Features:** Unlike the Nukkit server, Allay uses a lot of new protocol features that have been introduced by BDS, such as server-side authoritative inventory, new chunk format, sub-chunk packet sending, etc.
- **High Code Quality:** We pay a lot of attention to code quality and keep the project stable with the help of extensive unit testing and refactoring.

## Getting Started

### For Normal Users

Check the [Installation](https://docs.allaymc.org/getting-started/installation/) page in our docs.

### Run from Source Code

You may want to run Allay from source code and do some debugging, and that's quiet easy:

```shell
gradlew server:runShadow
```

## Plugins

Allay supports running plugins written in Java, Kotlin, Scala (JVM based language) and script languages such as JavaScript, Python, etc. You can check out our plugin templates:

- [JavaPluginTemplate](https://github.com/AllayMC/JavaPluginTemplate)
- [ScalaPluginTemplate](https://github.com/AllayMC/ScalaPluginTemplate)
- [KotlinPluginTemplate](https://github.com/MineBuilders/allaymc-kotlin-plugin-template)
- [JavaScriptPluginTemplate](https://github.com/AllayMC/JavaScriptPluginTemplate)

For more information, you can check our [documents](https://docs.allaymc.org/)

## Dependency

```kotlin
repositories {
    mavenCentral()
    maven("https://www.jitpack.io/")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    compileOnly(group = "org.allaymc.allay", name = "api", version = "master-SNAPSHOT")
}
```

We use jitpack to publish our artifacts. If you want to use a specified api version, 
you can check the version [here](https://jitpack.io/#AllayMC/Allay).

## Contribute

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) before submitting a PR!

This project exists thanks to the participation of the following developers:

![contributors](https://contrib.rocks/image?repo=AllayMC/Allay)

## Code Coverage

Feel free to help us refine our unit tests! More unit tests will help the project grow

![Codecov Graph](https://codecov.io/gh/AllayMC/Allay/graphs/sunburst.svg?token=EI8EDEKI51)

## Feedback

Your feedback can make this project better. If you find a problem/have a new idea, feel free to raise it in the [issues page](https://github.com/AllayMC/Allay/issues).

For anything else, please join our Discord group to discuss.

## Stargazers over time

[![Stargazers over time](https://starchart.cc/AllayMC/Allay.svg)](https://starchart.cc/AllayMC/Allay)

## License

Copyright **© 2023-2024 AllayMC**, all rights reserved.

If not otherwise specified, project content is open source under the LGPL-3.0 license.

The contents of the following folders are open-sourced under the MIT license:

- data
- codegen
