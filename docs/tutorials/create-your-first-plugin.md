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

## Initialize the Project

Open the project in IntelliJ IDEA and then open `README.md`. Follow the instructions in the file to initialize the project. After that,
reload the gradle project.

!!! tip

    If you are not using `JavaPluginTemplate`, you'll need to write the `plugin.json` file manually:
    
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

## Run the Server with the Plugin

If you are using the `AllayGradle` plugin (which is already included in `JavaPluginTemplate`), you can run the server directly from the project:

```bash
./gradlew runServer
```

## Build the Plugin

To build the plugin, run:

```bash
./gradlew shadowJar
```

The resulting file will be located at:

```text
build/libs/MyPlugin-1.0.0-shaded.jar
```

You can then copy it to the `plugins` directory of your Allay server and use it.

---

Now you're ready to create your own plugins for Allay! 🎉
