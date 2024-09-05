<!-- PROJECT LOGO -->
<br/>
<div align="center">

<a href="https://github.com/AllayMC/Allay">
    <img src="docs/assets/logo/allay-chan-640x.png" alt="Logo" width="200" height="200">
</a>
<h3 align="center">Allay</h3>

Серверное программное обеспечение для Minecraft: Bedrock Edition нового поколения

<a href="https://github.com/AllayMC/Allay/actions"><img src="https://github.com/AllayMC/Allay/actions/workflows/gradle.yml/badge.svg" alt="Build"/></a>
<a href="https://docs.allaymc.org/zh"><img src="https://readthedocs.org/projects/allaymc/badge/?version=latest" alt="Documentation Status"></a>
[![](https://jitpack.io/v/AllayMC/Allay.svg)](https://jitpack.io/#AllayMC/Allay)
[![codecov](https://codecov.io/gh/AllayMC/Allay/graph/badge.svg?token=EI8EDEKI51)](https://codecov.io/gh/AllayMC/Allay)
<a href="https://app.codacy.com/gh/AllayMC/Allay/dashboard"><img src="https://app.codacy.com/project/badge/Grade/30e264923da2425a8b777a84b4028334"></a>
<a href="https://discord.gg/ngkkE4hPTU"><img src="https://img.shields.io/discord/1147136608290750526?label=discord&color=7289DA&logo=discord" alt="Discord" /></a>
<a href="https://feedback.minecraft.net/hc/en-us/sections/360001186971-Release-Changelogs"><img src="https://img.shields.io/badge/minecraft-v1.21.20%20(Bedrock)-green" /></a>
<img src="https://img.shields.io/badge/protocol-712-blue">

[English](README.md) | [简体中文](README.zh.md) | Русский
</div>

## Введение

[//]: # (Allay - самое миленькое программное обеспечение в мире!)

Allay — это стороннее серверное программное обеспечение для Minecraft: Bedrock Edition, написанное на Java, с целью
обеспечить высокую производительность и масштабируемость благодаря тщательно продуманной архитектуре. Для получения
дополнительной информации ознакомьтесь с нашим [Q&A](docs/Q&A.zh.md)

> [!IMPORTANT]
> Обратите внимание, что данный проект находится на очень ранней стадии разработки и еще не выпущен в стабильной версии.
> Многие интерфейсы могут быть добавлены или удалены без предварительного уведомления. Пожалуйста, не используйте Allay
> в производственной среде.
>
> Вы можете просмотреть наш RoadMap, чтобы узнать о ходе разработки.

## Название

Как вы могли заметить, наш проект называется Allay, что является именем одного из существ в Minecraft.

Мы надеемся, что этот проект будет таким же простым, надежным и эффективным, как и Allay.

## Особенности

- **Кроссплатформенность:** Allay работает на JVM, поэтому может запускаться на большинстве платформ, поддерживающих
  JVM.
- **Высокая производительность:**
    - Мы хорошо понимаем проблемы серверов на ядре Nukkit в условиях высокой нагрузки. Allay в некоторых аспектах (
      например, физика сущностей) имеет производительность, превышающую Nukkit почти в сто раз при той же нагрузке.
    - Благодаря переработанной модели потоков, Allay может эффективно использовать многоядерные процессоры. Это значит,
      что вам не нужно специально использовать процессоры с высокой тактовой частотой.
    - Allay использует Java 21, что теоретически обеспечивает лучшую производительность.
- **Простота использования:**
    - Вы можете писать плагины для Allay на языках Java/JVM.
    - Мы добавили поддержку GraalVM и JavaScript, что позволяет писать плагины на JavaScript/TypeScript с такой же
      производительностью и бесшовной интеграцией, как и на Java.
- **Высокая настраиваемость:** Allay предоставляет множество интерфейсов, которых нет в BDS. Кроме того, вы даже можете
  контролировать отправку пакетов для максимальной настройки.
- **Безопасность:**
    - Allay проверяет клиентские пакеты данных более тщательно по сравнению с BDS, что теоретически устраняет многие
      известные уязвимости BDS.
    - Allay по умолчанию включает шифрование сети. Кроме того, в Allay встроена функция шифрования ресурсных пакетов,
      которая автоматически шифрует ресурсные пакеты, отправляемые клиенту, что в определенной степени защищает ваши
      данные от утечек.
- **Множество новых функций:** В отличие от серверов на базе Nukkit, Allay использует множество новых функций протокола,
  уже введенных в BDS, включая серверную авторизацию инвентаря, отправку sub-чанков и многое другое.
- **Качество кода:** Мы придаем большое значение качеству кода и поддерживаем стабильность проекта с помощью большого
  количества юнит-тестов и рефакторинга.

## Начало работы

Allay основан на Java 21, поэтому перед запуском и сборкой Allay вам нужно установить Java 21.
Если у вас есть необходимость разрабатывать JavaScript плагины, мы рекомендуем использовать GraalVM для достижения
наилучшей производительности.

**Запуск:**

```shell
gradlew Allay-Server:runShadow
```

**Сборка:**

```shell
gradlew Allay-Server:build
```

## Плагины

Allay поддерживает плагины, написанные на языках Java/JVM или JavaScript. Вы можете ознакомиться с примерами плагинов,
чтобы понять, с чего начать разработку:

**Пример на Java**: [Allay-ExamplePlugin](Allay-ExamplePlugin)

**Пример на JavaScript**: [@Allay-ExamplePlugin-JS](@Allay-ExamplePlugin-JS)

Для получения дополнительной информации посетите нашу [Документацию](https://docs.allaymc.org/zh/).

## Участие в проекте

Присоединяйтесь! Прежде чем отправлять PR, пожалуйста, прочтите [CONTRIBUTING.md](CONTRIBUTING.md).

Существование этого проекта стало возможным благодаря участию следующих разработчиков:

![contributors](https://contrib.rocks/image?repo=AllayMC/Allay)

## Покрытие кода

Помогите нам улучшить юнит-тесты! Юнит-тестирование способствует развитию этого проекта.

![Codecov Graph](https://codecov.io/gh/AllayMC/Allay/graphs/sunburst.svg?token=EI8EDEKI51)

## Обратная связь

Ваши отзывы помогут сделать этот проект лучше. Если вы обнаружили проблему или у вас есть новая идея, пожалуйста,
сообщите об этом на странице [issues](https://github.com/AllayMC/Allay/issues).

Для обсуждения других вопросов присоединяйтесь к нашему [Discord-сообществу](https://discord.gg/ngkkE4hPTU).

## Звезды!

[![Stargazers over time](https://starchart.cc/AllayMC/Allay.svg)](https://starchart.cc/AllayMC/Allay)

## Лицензия

Все права защищены **© 2023-2024 AllayMC**.

Если не указано иное, содержимое проекта распространяется по лицензии LGPL-3.0.

Содержимое следующих папок распространяется по лицензии MIT:

- Allay-Data
- Allay-CodeGen
