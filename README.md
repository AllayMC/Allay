<!-- PROJECT LOGO -->
<br/>
<div align="center">

<a href="https://github.com/AllayMC/Allay">
    <img src="docs/assets/logo/allay-chan-640x.png" alt="Logo" width="200" height="200">
</a>
<h3 align="center">Allay</h3>

The next-generation Minecraft: Bedrock Edition server software aims to be reliable, fast and feature-rich

<a href="https://github.com/AllayMC/Allay/actions"><img src="https://github.com/AllayMC/Allay/actions/workflows/gradle.yml/badge.svg" alt="Build"/></a>
<a href="https://docs.allaymc.org"><img src="https://readthedocs.org/projects/allaymc/badge/?version=latest" alt="Documentation Status"></a>
[![](https://jitpack.io/v/AllayMC/Allay.svg)](https://jitpack.io/#AllayMC/Allay)
[![codecov](https://codecov.io/gh/AllayMC/Allay/graph/badge.svg?token=EI8EDEKI51)](https://codecov.io/gh/AllayMC/Allay)
<a href="https://app.codacy.com/gh/AllayMC/Allay/dashboard"><img src="https://app.codacy.com/project/badge/Grade/30e264923da2425a8b777a84b4028334"></a>
<a href="https://discord.gg/ngkkE4hPTU"><img src="https://img.shields.io/discord/1147136608290750526?label=discord&color=7289DA&logo=discord" alt="Discord" /></a>
<a href="https://feedback.minecraft.net/hc/en-us/sections/360001186971-Release-Changelogs"><img src="https://img.shields.io/badge/minecraft-1.21.80 ~ 1.21.100%20(Bedrock)-green" /></a>
[![GitHub License](https://img.shields.io/github/license/allaymc/allay)](LICENSE)
[![Ko-Fi](https://img.shields.io/badge/Buy_Me_a_Coffee-f37574?logo=kofi&logoColor=white)](https://ko-fi.com/AllayMC)

</div>

## 📖 Introduction

Allay is a third-party server software for Minecraft: Bedrock Edition written from scratch, with the goal of being
reliable, fast and feature-rich.

## 🎯 Why Allay?

- **Fast**: Allay is very fast. Compared to almost all other server software, Allay is far more performant in almost all aspects. The written from
scratch codebase allows us to re-examine performance critical points and solve problems in the most efficient way.
- **Reliable**: Allay is designed to be reliable. To achieve this goal, we have written a lot of unit tests to ensure the stability of the project.
- **Hassle-free**: Allay is designed to be user-friendly and is ready to use right out of the box. We also have complete documentation to guide you 
in your deployment. 
- **Developer-friendly**: Our api is seperated from the implementation and is well-documented. No more wasting time looking at the implementation.
- **Feature-rich**: One of our goal is to be feature-rich. Thanks to the architecture of Allay, adding new features is very easy for us.
- **Cross-platform**: Benefit from JVM, allay can run on almost any platform.
- **Multi-version**: Allay has multi-version support. No longer worry about losing your players due to version updates.

## 🛠️ Getting Started

Allay is written and running in java 21, so you need to install java 21. There are
several version of java, and we recommend you to use [GraalVM](https://www.graalvm.org/) for the best performance.
[OpenJDK](https://adoptopenjdk.net/) is also a good choice if you want to have a stable experience.

After you have installed java 21, there are two ways to install Allay:

### ⚡ Use AllayLauncher

[AllayLauncher](https://github.com/AllayMC/AllayLauncher) is the launcher for allay. It has some cool features such as auto-updating, daemon mode
and java checker. To use it, just run the following command based on your OS:

Linux:

```bash
wget -qO- https://raw.githubusercontent.com/AllayMC/AllayLauncher/refs/heads/main/scripts/install_linux.sh | bash
```

Windows (PowerShell):

```powershell
Invoke-Expression (Invoke-WebRequest -Uri "https://raw.githubusercontent.com/AllayMC/AllayLauncher/refs/heads/main/scripts/install_windows.ps1").Content
```

### 🔨 Install Allay Manually

If you platform does not support AllayLauncher, don't be worry! You can also install Allay manually. Just
download the `allay-server-*-shaded.jar` file from our release page. Move it to the folder you want and run:

```shell
java -jar allay-server-*-shaded.jar
```

## 🌟 Plugins

Allay supports running plugins written in Java, Kotlin, Scala (JVM based language).

### Use Plugin Template

The quickest way to create a new plugin is to use our plugin template!
You can check out the following plugin templates:

- [JavaPluginTemplate](https://github.com/AllayMC/JavaPluginTemplate)
- [ScalaPluginTemplate](https://github.com/AllayMC/ScalaPluginTemplate)
- [KotlinPluginTemplate](https://github.com/MineBuilders/allaymc-kotlin-plugin-template)

### Use Allay in Existing Project

If you have an existing project and want to use Allay in it, you can add the following
dependencies to your project:

```kts
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

Currently, we use jitpack to publish our artifacts. If you want to use a specified version,
you can check the version list in [jitpack](https://jitpack.io/#AllayMC/Allay) and replace
`master-SNAPSHOT` with the version you want.

## 🙌 Contributing

Contributions are welcomed! And please read [CONTRIBUTING.md](CONTRIBUTING.md) before contributing.
There are several ways you can contribute:

### Submitting a Pull Request

We appreciate code contributions. If you've fixed a bug or implemented a new feature, please submit
a pull request! Please ensure your code follows our coding standards and include tests where possible.

This project exists thanks to the participation of the following developers:

![contributors](https://contrib.rocks/image?repo=AllayMC/Allay)

### Feedback

Your feedback can make this project better. If you find a problem/have a new idea, feel free to raise it in
the [issues page](https://github.com/AllayMC/Allay/issues). Ensure to include a detailed description of the 
bug and steps to reproduce it.

## 🎫 License

Copyright **© 2023-2025 AllayMC**, all rights reserved. If not otherwise specified, project content is open source under
the LGPL-3.0 license. The contents of the following folders are open-sourced under the MIT license:

- data
- codegen

## ❤️ Special Thanks

- [Endstone](https://github.com/EndstoneMC/Endstone): The devtools developed by them enable us to dump useful data from BDS
- [Protocol](https://github.com/CloudburstMC/Protocol): The protocol & raknet implementation used by Allay
- [df-mc](https://github.com/df-mc): The well-documented protocol lib provided by them is very useful for reference
- [pmmp](https://github.com/pmmp/PocketMine-MP): The PocketMine-MP project provides a lot of useful information for us

![IntelliJ IDEA logo](https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.svg)

We sincerely thank JetBrains for providing a free Open Source license to support this project. Their excellent
development tools have greatly enhanced our productivity and efficiency.

[![YourKit-Logo](https://www.yourkit.com/images/yklogo.png)](https://www.yourkit.com/)

YourKit has granted AllayMC team an open-source license to their
outstanding Java profiler, allowing us to make our software as performant as it
can be!

YourKit supports open source projects with innovative and intelligent tools for
monitoring and profiling Java and .NET applications. YourKit is the creator of
the [YourKit Java Profiler](https://www.yourkit.com/java/profiler/), [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/),
and [YourKit YouMonitor](https://www.yourkit.com/youmonitor/).

## 👀 Stargazers over time

[![Stargazers over time](https://starchart.cc/AllayMC/Allay.svg)](https://starchart.cc/AllayMC/Allay)

[//]: # (Allay is the cutest software in the world!)
