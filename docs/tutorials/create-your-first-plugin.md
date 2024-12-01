---
comments: true
---

# Create Your First Plugin

Welcome to Allay! Allay provides a powerful plugin system so that you can write plugin in many languages,
such as Java, JavaScript, Scala and Kotlin. In this section, we will guide you to create your first plugin in Java.

## Prerequisites

To develop your first plugin, you will have to install the following prerequisites:

- [JetBrains IDEA](https://www.jetbrains.com/idea/)
- [JDK 21](../getting-started/installation.md#install-java)
- [Allay](../getting-started/installation.md#download-allay)

## Creating a New Project

We have plugin template that you can use to create your project quickly:

- [JavaPluginTemplate](https://github.com/AllayMC/JavaPluginTemplate)
- [JavaScriptPluginTemplate](https://github.com/AllayMC/JavaScriptPluginTemplate) (requires [ScriptPluginExtension](https://github.com/AllayMC/ScriptPluginExtension))
- [ScalaPluginTemplate](https://github.com/AllayMC/ScalaPluginTemplate)
- [KotlinPluginTemplate](https://github.com/MineBuilders/allaymc-kotlin-plugin-template)

And because you are going to write your plugin in java, we choose the `JavaPluginTemplate` to create a new project:

- Click on the green `Use this template` button and choose `Create a new repository` on the repository page.
- Enter the repository name and description. The name should be in camel-case, in this case, we will use `MyPlugin` as the name.
- Click on the green `Create repository` button.

Wait a moment for the repository to be created, and you will be redirected to the repo page of your new project.
Then we can clone the repository to our local machine.

## Edit the Project Information

After cloning the repository, open the project in IDEA, and we need to change some information:

- Rename package name from `org.allaymc.javaplugintemplate` to `your.group.name.myplugin`.
- Rename `src/main/java/your/group/name/myplugin/JavaPluginTemplate.java` to `src/main/java/your/group/name/myplugin/MyPlugin.java`, 
  there are also some places that you need to change inside the class. **Please make sure that this class (the main class) has a default constructor**.
- Find `build.gradle.kts` file, change `group` to `your.group.myplugin`, 
  also don't forget to change `description`.
- Find `settings.gradle.kts` file, change `rootProject.name` to `MyPlugin`.
- Find `src/main/resources/plugin.json` file:
  - Change `entrance` to `your.group.name.myplugin.MyPlugin`.
  - Change `name` to `MyPlugin`.
  - Change `authors` to `["yourname"]`
- Reload gradle

## Build and Install Your Plugin

To build your plugin into a jar file, you need to run the following command:

```shell
gradlew shadowJar
```

And this command will produce a file named `MyPlugin-1.0.0-shaded.jar` in the `build/libs` directory.
Copying this file to the `plugins` directory of your Allay server, and start the server
to check if your plugin loads and operates as expected.