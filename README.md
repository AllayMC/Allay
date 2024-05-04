<!-- PROJECT LOGO -->
<br/>
<div align="center">

<a href="https://github.com/AllayMC/Allay">
    <img src="docs/assets/logo/allay-chan-640x.png" alt="Logo" width="200" height="200">
</a>
<h3 align="center">Allay</h3>

The next-generation Minecraft: Bedrock Edition server software

<a href="https://github.com/AllayMC/Allay/actions"><img src="https://github.com/AllayMC/Allay/actions/workflows/gradle.yml/badge.svg" alt="Build"/></a>
<a href="https://app.codacy.com/gh/AllayMC/Allay/dashboard"><img src="https://app.codacy.com/project/badge/Grade/30e264923da2425a8b777a84b4028334"></a>
<a href="https://discord.com/invite/wyN2rVJt"><img src="https://img.shields.io/discord/373199722573201408?label=discord&color=7289DA&logo=discord" alt="Discord" /></a>
<a href="https://feedback.minecraft.net/hc/en-us/sections/360001186971-Release-Changelogs"><img src="https://img.shields.io/badge/minecraft-v1.20.72%20(Bedrock)-green" /></a>
<img src="https://img.shields.io/badge/protocol-662-blue">

English | [简体中文](README.zh.md)
</div>

## Introduction

[//]: # (Allay is the cutest software in the world!)

Allay is a third-party server software for Minecraft: Bedrock Edition written in Java with the goal of maintaining high scalability while maintaining high performance through a well-designed architecture. 
For more information, check out our [Q&A](docs/Q&A.zh.md) (need to be translated).

> [!IMPORTANT]
> Please note that this project is still at a very early stage and has not yet been released officially, and a large number of interfaces may be added or removed without notice. Please do not use Allay in a production environment.
>
> You can check our roadmap for development progress.

## Name

As you can see，our project is called Allay，the name of an entity in Minecraft

We wish this project to be as concise, reliable, and efficient as Allay.

## Features

- **Cross-platform:** Allay is based on the JVM, so it can run on most platforms that can run the JVM.
- **High Performance:**
    - We fully understand the problems of Nukkit server under high load, Allay outperforms Nukkit in certain aspects (eg: entity physics) by a factor of 100 under the same load.
    - In addition, thanks to a redesigned threading model, Allay takes full advantage of multi-core CPUs, which means you don't need to intentionally use high CPU frequencies.
    - Allay is based on the latest Java21, which theoretically gives it better performance.
- **Easy to get started:**
    - You can write plugins for Allay in Java/JVM.
    - We introduced GraalVM and JavaScript support, which means you can write plugins in JavaScript/TypeScript and
      get the same performance as Java and the ability to interoperate seamlessly.
- **Highly Customizable:** Allay offers a large number of interfaces that BDS does not. On top of that, you can even control the sending of packets directly for maximum customization.
- **Security:**
    - Allay has more checks on client packets than BDS, and theoretically does not have many of the pernicious vulnerabilities that BDS has.
    - Allay has network encryption enabled by default. In addition, Allay has a built-in resource packet encryption function that automatically encrypts resource packets sent to the client, preventing your data from leaking to a certain extent.
- **Lots of new features:** Unlike the Nukkit server, Allay uses a lot of new protocol features that have been introduced by BDS, including, but not limited to, the server-side authoritative item bar, and sub-block packet sending.
- **Code Quality:** We pay a lot of attention to code quality and keep the project stable with the help of extensive unit testing and refactoring.

## Getting Started

Allay is based on Java21, so you need to install Java21 before running & building Allay.
If you need to develop scripting plugins, we recommend using GraalVM for best performance.

**Run directly:**
```shell
gradlew Allay-Server:runShadow
```

**Build:**
```shell
gradlew Allay-Server:build
```

## Plugins

Allay supports running plugins written in Java/JVM or JavaScript languages, you can check out the example plugins to see how to get started:

**Java example**: [Allay-ExamplePlugin](Allay-ExamplePlugin)

**JavaScript example**: [@Allay-ExamplePlugin-JS](@Allay-ExamplePlugin-JS)

## Contribute

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) before submitting a PR!

This project exists thanks to the participation of the following developers:

![contributors](https://contrib.rocks/image?repo=AllayMC/Allay)

## Feedback

Your feedback can make this project better. If you find a problem/have a new idea, feel free to raise it in the [issues page](https://github.com/AllayMC/Allay/issues).

For anything else, please join our Discord group to discuss.

## Stargazers over time

[![Stargazers over time](https://starchart.cc/AllayMC/Allay.svg)](https://starchart.cc/AllayMC/Allay)

## License

Copyright **© 2023-2024 AllayMC**, all rights reserved.

If not otherwise specified, project content is open source under the LGPL-3.0 license.

The contents of the following folders are open-sourced under the MIT license:

- Allay-Data
- Allay-CodeGen
