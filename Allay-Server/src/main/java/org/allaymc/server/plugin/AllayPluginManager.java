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
    protected DAG<String> dag = new DAG<>();

    public AllayPluginManager() {
        registerSource(new DefaultPluginSource());
        registerLoaderFactory(new JarPluginLoader.JarPluginLoaderFactory());
    }

    @Override
    public void loadPlugins() {
        // 1. Load possible plugin paths from plugin sources
        Set<Path> paths = findPluginPaths();

        // 2. Find and use plugin loader to load plugin descriptor for each plugin path
        Map<String, PluginDescriptor> descriptors = new HashMap<>();
        Map<String, PluginLoader> loaders = new HashMap<>();
        findLoadersAndLoadDescriptors(paths, descriptors, loaders);

        // 3. Check for circular dependencies
        checkCircularDependencies(descriptors);

        // 4. Load plugins (parent -> child)
        onLoad(descriptors, loaders);
    }

    protected void findLoadersAndLoadDescriptors(Set<Path> paths, Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> loaders) {
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
            loaders.put(name, loader);
        }
    }

    private record Result(Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> loaders) {
    }

    protected void onLoad(Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> loaders) {
        dag.visit(node -> {
            var descriptor = descriptors.get(node.getObject());
            var loader = loaders.get(node.getObject());
            var pluginContainer = loader.loadPlugin();
            plugins.put(descriptor.getName(), pluginContainer);
            pluginContainer.plugin().onLoad();
        });
    }

    protected void checkCircularDependencies(Map<String, ? extends PluginDescriptor> descriptors) {
        try {
            // Add all plugin names to dag firstly
            descriptors.keySet().forEach(dag::createNode);
            for (var descriptor : descriptors.values()) {
                for (var dependency : descriptor.getDependencies()) {
                    var depNode = dag.getNode(dependency.name());
                    if (depNode == null) {
                        if (!dependency.optional())
                            throw new PluginException("Plugin " + descriptor.getName() + " require a dependency plugin " + dependency.name() + " which is not exists!");
                        else continue;
                    }
                    depNode.addChild(dag.getNode(descriptor.getName()));
                }
            }
            dag.update();
        } catch (CycleFoundException cfe) {
            throw new PluginException(cfe);
        }
    }

    protected Set<Path> findPluginPaths() {
        Set<Path> paths = new HashSet<>();
        for (var source : sources) {
            paths.addAll(source.find());
        }
        return paths;
    }

    @Override
    public void enablePlugins() {
        dag.visit(node -> {
            var pluginContainer = getPluginContainer(node.getObject());
            pluginContainer.plugin().onEnable();
        });
    }

    @Override
    public void disablePlugins() {
        dag.visit(node -> {
            var pluginContainer = getPluginContainer(node.getObject());
            pluginContainer.plugin().onDisable();
        });
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
    public Map<String, PluginContainer> getPluginContainers() {
        return Collections.unmodifiableMap(plugins);
    }

    @Override
    public PluginContainer getPluginContainer(String name) {
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
