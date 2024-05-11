---
comments: true
---

# How Plugin Works

Plugin is one of the important parts in Allay. Before we write our first plugin, it is necessary for us to know how a plugin is loaded and how it works.

## Constituent

`PluginSource`: Provides the paths of plugins(local or remote).
User can register their own plugin source, and the system will traverse all plugin sources when loading plugins.

`PluginLoader(Factory)`: For each plugin file, the system will find its suitable loader.
(By traversing all registered plugin loader factories and call `PluginLoaderFactory.canLoad(path)` in order)
If found, the system will create a loader instance. 
Users can register their own custom plugin loader factories 
to support plugins which are written in different kinds of languages.

`PluginDescriptor`: Including series of information of a plugin. For example, API version, entrance, dependencies etc...

`PluginManager`: Including the logic of loading and running plugins.

`PluginContainer`: A running container will be created after the plugin loaded, which including the plugin's logic body, descriptor, loader and data folder.

`Plugin`: The logic body of a plugin。This object is used by the system to interact with a plugin, 
which typically contains at least the logic that will be executed when the plugin is loaded, enabled, and unloaded

## Loading process

When the server starts, the plugin manager first traverses all plugin sources and looks for the loader for each plugin file.

Next, the manager will load the descriptor for each plugin and check for loop-back dependencies.

Once everything is done, call the plugin's `onLoad()` method. Note that this does not mean that the server has been started, and you should not do anything related to the game content, including but not limited to:

- Register the listener
- Register commands
- Register a scheduled task
- Interact with the world
- ...

When a plugin is enabled, the plugin's `onEnable()` method is called. Correspondingly, the `onDisable()` method of the plugin is called when the plugin is closed.

When you uninstall a plugin, the `onUnload()` method of the plugin is called.