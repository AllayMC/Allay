---
comments: true
---

## About Plugin Hot Reload

Plugin hot reload has been proven to be unreliable and unstable in the Nukkit community. Theoretically, a well-written
plugin should support hot reload, but in reality, the skill levels of community users vary greatly. We have seen too
many poorly written plugins, whose behaviors include but are not limited to:

- Completing all necessary tasks for plugin startup in the `onLoad()` method, such as registering listeners, commands,
  etc.
- Not writing plugin unload logic, resulting in listeners, commands, and scheduled tasks registered by this plugin still
  being available after the plugin is unloaded.
- ...

Considering the above reasons, for a Jar plugin, we default that it cannot be hot reloaded. However, we still allow
users to mark their plugins as "reloadable", which is achieved by overriding the `Plugin.isReloadable()` method and
returning `true`.

Keep in mind that overriding the method `Plugin.isReloadable()` and returning `true` doesn't mean that your plugin supports hot reloading.
You still need to make sure your code supports hot reloading.
You'll need to override the `Plugin.reload()` method, and the details will be introduced later.
If you only override the method `Plugin.isReloadable()` without overwriting `Plugin.reload()`, the console will output a warning

There used to be a wonderful operation in the Nukkit community years ago. Since Nukkit does not lock plugin files for writing, users modify
the code, recompile it, replace the original jar file with the new one, and call the plugin's `reload()` method. The new
code miraculously takes effect. I admit that this is indeed very convenient (in fact, I have done it myself), but in
Allay, Jar plugins are not allowed to do so.

Remember, for Jar plugins, calling the `reload()` method **does not mean it is actually reloaded from the file**.

## Making Your Plugin Support Hot Reload

- Override the method `Plugin.isReloadable()` and return `true`.
- Override the method `Plugin.reload()`. For simple plug-ins, the most common action is to re-read the plug-in configuration file.

## Hot Reload for JavaScript Plugins

Unlike Jar plugins, JS plugins support hot reload by default. When calling the `reload()` method of a JS plugin, the
current JS context is completely destroyed and recreated. We do not lock the files for JS plugins, which means you can
modify the code while the plugin is running and reload it to make the changes take effect, which is particularly
convenient.
