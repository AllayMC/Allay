package org.allaymc.server.plugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.dag.CycleFoundException;
import org.allaymc.api.datastruct.dag.DAG;
import org.allaymc.api.plugin.*;
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

    protected Set<PluginSource> sources = new HashSet<>();
    protected Set<PluginLoader.PluginLoaderFactory> loaderFactories = new HashSet<>();
    protected Map<String, PluginContainer> plugins = new HashMap<>();

    public AllayPluginManager() {
        registerLoaderFactory(new JarPluginLoader.JarPluginLoaderFactory());
    }

    @Override
    public void loadPlugins() {
        // 1. Load possible plugin paths from plugin sources
        Set<Path> paths = new HashSet<>();
        for (var source : sources) {
            paths.addAll(source.find());
        }

        // 2. Find and use plugin loader to load plugin descriptor for each plugin path
        Map<String, PluginDescriptor> descriptors = new HashMap<>();
        for (var path : paths) {
            var loader = findLoader(path);
            if (loader == null) continue;
            var descriptor = loader.loadDescriptor();
            var name = descriptor.getName();
            if (descriptors.containsKey(name)) {
                log.warn("Duplicate plugin is found, name: " + name);
                continue;
            }
            descriptors.put(name, descriptor);
        }

        // 3. Check for circular dependencies
        var dag = new DAG<String>();
        try {
            for (var descriptor : descriptors.values()) {
                var node = dag.getOrCreateNode(descriptor.getName());
                for (var dependency : descriptor.getDependencies()) {
                    var depNode = dag.getOrCreateNode(dependency.name());
                    node.addParent(depNode);
                }
            }
            dag.update();
        } catch (CycleFoundException cfe) {
            log.error(cfe.getMessage());
            throw cfe;
        }

        // 4. Load plugins (parent -> child) TODO
    }

    @Override
    public void registerLoaderFactory(PluginLoader.PluginLoaderFactory loaderFactory) {
        loaderFactories.add(loaderFactory);
    }

    @Override
    public void registerSource(PluginSource pluginSource) {
        sources.add(pluginSource);
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
