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

Please note that overriding the `Plugin.isReloadable()` method and returning `true` does not mean your plugin supports
hot reload. You still need to ensure that your code supports hot reload. Before introducing how to make your plugin
support hot reload, let's first see what the `Plugin.reload()` method does:

```java
public final void reload() {
    if (!isReloadable()) throw new UnsupportedOperationException("This plugin is not a reloadable plugin!");
    onDisable();
    onUnload();
    onLoad();
    onEnable();
}
```

The `Plugin.reload()` method simulates the complete process of uninstalling and reloading the plugin. There used to be a
wonderful operation in the Nukkit community years ago. Since Nukkit does not lock plugin files for writing, users modify
the code, recompile it, replace the original jar file with the new one, and call the plugin's `reload()` method. The new
code miraculously takes effect. I admit that this is indeed very convenient (in fact, I have done it myself), but in
Allay, Jar plugins are not allowed to do so.

Remember, for Jar plugins, calling the `reload()` method **does not mean it is actually reloaded from the file**.

## Making Your Plugin Support Hot Reload

**Ensure that operations related to plugin functionality are in the `onEnable()` method**, such as:

- Registering listeners
- Registering scheduled tasks
- Registering commands
- ...

**Ensure that all content registered by the plugin is unregistered in the `onDisable()` method**.

Even if the above is followed, we still cannot guarantee that your plugin can be hot reloaded correctly. **For Jar
plugins, the best way is not to support hot reload**, as restarting the server does not take much time.

## Hot Reload for JavaScript Plugins

Unlike Jar plugins, JS plugins support hot reload by default. When calling the `reload()` method of a JS plugin, the
current JS context is completely destroyed and recreated. We do not lock the files for JS plugins, which means you can
modify the code while the plugin is running and reload it to make the changes take effect, which is particularly
convenient.
