package org.allaymc.server.plugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.PluginContainer;
import org.allaymc.api.plugin.PluginLoader;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.server.plugin.jar.JarPluginLoader;

import java.nio.file.Path;
import java.util.*;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayPluginManager implements PluginManager {

    protected Set<PluginLoader.PluginLoaderFactory> loaderFactories = new HashSet<>();
    protected Map<String, PluginContainer> plugins = new HashMap<>();

    public AllayPluginManager() {
        registerLoaderFactory(new JarPluginLoader.JarPluginLoaderFactory());
    }

    @Override
    public void loadPlugins() {
        // TODO
    }

    @Override
    public void registerLoaderFactory(PluginLoader.PluginLoaderFactory loaderFactory) {
        loaderFactories.add(loaderFactory);
    }

    @Override
    public Map<String, PluginContainer> getPlugins() {
        return Collections.unmodifiableMap(plugins);
    }

    @Override
    public PluginContainer getPlugin(String name) {
        return plugins.get(name);
    }

    protected PluginLoader findLoader(Path pluginPath) {
        for (var loaderFactory : loaderFactories) {
            if (loaderFactory.canLoad(pluginPath)) return loaderFactory.create(pluginPath);
        }
        log.warn("Can't find loader for plugin " + pluginPath);
        return null;
    }
}
