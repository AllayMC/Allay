<!-- PROJECT LOGO -->
<br/>
<div align="center">

<a href="https://github.com/AllayMC/Allay">
    <img src="docs/assets/logo/allay-chan-640x.png" alt="Logo" width="200" height="200">
</a>
<h3 align="center">Allay</h3>

下一代的 Minecraft: Bedrock Edition 服务端软件

<a href="https://github.com/AllayMC/Allay/actions"><img src="https://github.com/AllayMC/Allay/actions/workflows/gradle.yml/badge.svg" alt="Build"/></a>
<a href="https://app.codacy.com/gh/AllayMC/Allay/dashboard"><img src="https://app.codacy.com/project/badge/Grade/30e264923da2425a8b777a84b4028334"></a>
<a href="https://discord.gg/jAEczu23"><img src="https://img.shields.io/discord/373199722573201408?label=discord&color=7289DA&logo=discord" alt="Discord" /></a>
<a href="https://feedback.minecraft.net/hc/en-us/sections/360001186971-Release-Changelogs"><img src="https://img.shields.io/badge/minecraft-v1.20.72%20(Bedrock)-green" /></a>
<img src="https://img.shields.io/badge/protocol-662-blue">

[English](README.md) | 简体中文
</div>

## 介绍

[//]: # (Allay is the cutest software in the world!)

Allay是使用Java编写的Minecraft: Bedrock Edition第三方服务端软件，目标通过精心设计的架构来在保持高性能的同时保持高扩展性。有关更多信息，请查看我们的[Q&A](docs/zh/问&答)

> [!IMPORTANT]
> 请注意，此项目仍处于非常早期的阶段且还未释放正式版，大量接口可能会在没有预先通知的情况下增加或删除。请不要在生产环境使用Allay。
>
> 你可以查看我们的RoadMap来获取开发进度。

## 名称

如你所见，我们项目的名字叫做Allay，这是Minecraft中的一个生物的名字。

我们希望这个项目能像Allay一样简洁，可靠，高效。

## 特性

- **跨平台:** Allay基于JVM，故可以在大多数能运行JVM的平台上运行。
- **高性能:**
  - 我们充分了解Nukkit系服务端在高负载环境下存在的问题，Allay在同样的负载环境下于特定方面（eg: 实体物理）的性能比Nukkit高近百倍。
  - 除此之外，得益于重新设计的线程模型，Allay能充分利用多核CPU。这意味着你不需要刻意使用高频率的CPU。
  - Allay基于最新的Java21，理论上能获得更好的性能
- **易于上手:**
  - 你可以使用Java/JVM语言编写适用于Allay的插件
  - 我们引入了GraalVM和JavaScript支持，这意味着你可以使用JavaScript/TypeScript编写插件并
    获得与Java同等的性能以及无缝互操作的能力。
- **高自定义性:** Allay提供大量BDS不具备的接口。除此之外，你甚至可以直接控制发包来获得最大的自定义性。
- **安全:** 
  - Allay相较于BDS对客户端发包有更多的校验，理论上不存在BDS存在的许多恶性漏洞。
  - Allay默认开启网络加密。另外，Allay内置资源包加密功能，可自动加密发送给客户端的资源包，一定程度上防止你的数据泄漏。
- **大量新功能:** 不同于Nukkit系服务端，Allay使用了大量BDS已经引入的新的协议功能，包括但不限于服务端权威物品栏，子区块发包。。。
- **代码质量:** 我们非常注重代码质量，并借助大量的单元测试和重构保持项目稳定。

## 开始使用

Allay基于Java21，故在运行&构建Allay前你需要安装Java21。 
若你有开发脚本插件的需求，我们建议你使用GraalVM以获得最好性能。

**直接运行:**
```shell
gradlew Allay-Server:runShadow
```

**构建:**
```shell
gradlew Allay-Server:build
```

## 插件

Allay支持运行以Java/JVM或JavaScript语言编写的插件，你可以查看示例插件来了解如何开始编写：

**Java示例**: [Allay-ExamplePlugin](Allay-ExamplePlugin)

**JavaScript示例**: [@Allay-ExamplePlugin-JS](@Allay-ExamplePlugin-JS)

## 贡献

欢迎参与！在提交PR之前，请先阅读[CONTRIBUTING.md](CONTRIBUTING.md)

这个项目的存在得益于以下开发者的参与：

![contributors](https://contrib.rocks/image?repo=AllayMC/Allay)

## 反馈

你的反馈能让这个项目变得更好。若你发现了一个问题/有一个新的想法，欢迎在[issues界面](https://github.com/AllayMC/Allay/issues)提出。

其他内容请加入我们的Discord群组讨论。

## 星星！

[![Stargazers over time](https://starchart.cc/AllayMC/Allay.svg)](https://starchart.cc/AllayMC/Allay)

## 许可证

版权所有 **© 2023-2024 AllayMC**，保留所有权利。

若未特别指明，项目内容均采用LGPL-3.0协议开源。

以下文件夹内的内容以MIT协议开源：

- Allay-Data
- Allay-CodeGen