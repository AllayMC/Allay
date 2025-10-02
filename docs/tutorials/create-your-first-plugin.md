---
comments: true
---

# Create Your First Plugin

Welcome to **Allay**! Allay provides a powerful plugin system that allows you to write plugins in various languages —
Java, Kotlin, Scala and any JVM-based language. In this section, we'll walk you through creating a simple plugin in Java.

## Prerequisites

Before you begin, make sure you have the following installed:

- [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [JDK 21](../getting-started/installation.md#install-java)
- [Allay](../getting-started/installation.md#download-allay)

## Project Templates

To quickly get started, you can use one of the available plugin templates:

- [JavaPluginTemplate](https://github.com/AllayMC/JavaPluginTemplate)
- [KotlinPluginTemplate](https://github.com/MineBuilders/allaymc-kotlin-plugin-template)
- [ScalaPluginTemplate](https://github.com/AllayMC/ScalaPluginTemplate)

In this guide, we will use **JavaPluginTemplate**.

## Creating a New Project

1. Go to the [JavaPluginTemplate](https://github.com/AllayMC/JavaPluginTemplate) page.
2. Click the `Use this template` button → `Create a new repository`.
3. Enter a name and description. We'll use `MyPlugin` as the name.
4. Click `Create repository`.

Once the repository is created, you'll be redirected to its page. Clone the project to your local machine:

```bash
git clone https://github.com/yourusername/MyPlugin.git
cd MyPlugin
```

## Editing the Project

Open the project in IntelliJ IDEA and follow these steps:

1. **Change the package name:**
   Replace `org.allaymc.javaplugintemplate` with `your.group.name.myplugin`.
2. **Rename the main class:**
   Rename the file:

   ```text
   src/main/java/org/allaymc/javaplugintemplate/JavaPluginTemplate.java
   → 
   src/main/java/your/group/name/myplugin/MyPlugin.java
   ```

   Also change the class name inside the file. Make sure it has a default constructor.
3. **Update `build.gradle.kts`:**
   ```kotlin
   group = "your.group.name.myplugin"
   description = "My first Allay plugin"
   ```
4. **Update `settings.gradle.kts`:**
   ```kotlin
   rootProject.name = "MyPlugin"
   ```
5. **Edit `plugin.json`:**
   ```json linenums="1"
   {
     "name": "MyPlugin",
     "entrance": "your.group.name.myplugin.MyPlugin",
     "authors": ["yourname"],
     "version": "0.1.0",
     "api_version": ">=0.1.0",
     "description": "The description of your plugin",
     "website": "The website of your plugin",
     "dependencies": [
        {
            "name": "AnotherPlugin1"
        },
        {
            "name": "AnotherPlugin2",
            "version": ">=0.1.0",
            "optional": true
        }
    ]
   }
   ```
6. **Reload the Gradle project.**

When editing the plugin.json, there are a few things worth noting:

- Fields `api_version`, `description`, `website` and `dependencies` can be missing.
- The `version` of your plugin must be a valid [Semantic Version](https://semver.org/spec/v2.0.0.html), otherwise the plugin won't be loaded.
- The `api_version` of your plugin and the `version` of entries in `dependencies` can be an expression, and here are some examples:
  - Single version
    - `1.2.3`
  - NPM Style
    - `>1.2.2`
    - `1.1.1 || 1.2.3 - 2.0.0`
    - `1.1.*`
    - `~1.2.1`
    - `^1.1.1`
  - COCOAPODS Style
    - `> 1.2.2`
    - `~> 1.2.1`
    - `<= 1.1.1`
  - IVY Style
    - `1.2.+`
    - `(,1.8.9]`
    - `[0.2,1.4]`

## Building and Installing the Plugin

To build the plugin, run:

```bash
./gradlew shadowJar
```

The resulting file will be located at:

```text
build/libs/MyPlugin-1.0.0-shaded.jar
```

Copy it to the `plugins` directory of your Allay server and start the server.

## Quickly Run the Server with the Plugin

If you are using the `JavaPluginTemplate`, you can run the server directly from the project:

```bash
./gradlew runServer
```

This task will:

- Download the Allay server
- Build your plugin
- Place it in the `plugins` directory
- Start the server

---

Now you're ready to create your own plugins for Allay! 🎉
