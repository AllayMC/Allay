---
comments: true
---

# Version of Allay

This document details the version system of Allay.

## API Version and Server Version

Because Allay is a project that separates [API](https://github.com/AllayMC/Allay/tree/master/api)
and [implementation](https://github.com/AllayMC/Allay/tree/master/server),
there are two versions in Allay: API version and server version, and they are two things.

The version of API adhere to [Semantic Versioning](https://semver.org/spec/v2.0.0.html). Normally,
unless the plugin developer needs to complete some special functions, it should only depend on the
API version. If there is no changes to API module except changes to classes, packages and methods
that are annotated with `@MinecraftVersionSensitive` annotation, there should be no change to the
API version. In addition, changes in external libraries are not protected by API versions, they are
not considered as changes to the API.

The server version is the version of the server implementation. It is similar
to API version but has nothing to do with the API version. One thing to note
is that although plugin can also use interfaces in server implementation by adding
`allay-server` to dependencies, all interfaces in server implementation are considered
as internal interfaces, which will changed, moved or being deleted without notification.

The iteration rules of the server version are also different from API:

- The major version is synchronized with the major version of the api.
- The minor version only bumps when the protocol version of Minecraft is updated.
- The patch version is increased for each release, and will be reset when major/minor version is updated.

## When Minecraft Version is Updated

First of all, We need to distinguish between protocol version and game version.
About the details, you can refer to [wiki](https://minecraft.wiki/w/Protocol_version).

Keep in mind that Allay only care about the protocol version. When we talk about version updates,
we are usually referring to protocol version updates. Protocol version usually being updated
when the first number of the `patch` area of the minecraft version change. For example minecraft `1.21.50`
corresponds to protocol version `766`, and `1.21.40` corresponds to protocol version `748`.

For better understanding, Allay will mark both supported minecraft versions and protocol versions,
but for minecraft versions, we will only mark the first minecraft version that supports this
protocol version. For example, `1.21.40` actually refers to four versions:
`1.21.40`, `1.21.41`, `1.21.42`, `1.21.43` and `1.21.44` (they all support protocol version `748`),
however we only said that Allay support `1.21.40`.

## About `@MinecraftVersionSensitive` annotation

This annotation is used to mark the classes, packages and methods in API module that are
sensitive to the minecraft version. It means that they may change, have new content or
even being deleted during version updating. Here are some examples:

Classes:

- `BlockTypes.java`
- `ItemTypes.java`

Packages:

- `org.allaymc.api.block.property.enums`

Although they may not change frequently, we make no immutability guarantees on them,
and they are not protected by API versions. However, we decided to keep them in the
API to make the developers who believe that they are able to update their plugins during
version updating timely to develop plugins easier.
