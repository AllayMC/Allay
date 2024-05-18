package org.allaymc.server.plugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.dag.DAGCycleException;
import org.allaymc.api.datastruct.dag.HashDirectedAcyclicGraph;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.plugin.*;
import org.allaymc.server.plugin.jar.JarPluginLoader;
import org.allaymc.server.plugin.js.JsPluginLoader;

import java.nio.file.Path;
import java.util.*;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayPluginManager implements PluginManager {

    // TODO: check whether should we use concurrent collections
    protected Set<PluginSource> sources = new HashSet<>();
    protected Set<PluginLoader.PluginLoaderFactory> loaderFactories = new HashSet<>();
    protected Map<String, PluginContainer> plugins = new HashMap<>();
    protected HashDirectedAcyclicGraph<String> dag = new HashDirectedAcyclicGraph<>();
    protected Map<String, PluginContainer> enabledPlugins = new HashMap<>();
    protected List<String> pluginsSortedList;

    public AllayPluginManager() {
        registerSource(new DefaultPluginSource());
        // Only for testing example-plugin-js more convenient
        // Only works when the server is running in {project-dir}/run folder
        registerSource(() -> Set.of(
                Path.of("../@Allay-ExamplePlugin-JS")
        ));
        registerLoaderFactory(new JarPluginLoader.JarPluginLoaderFactory());
        registerLoaderFactory(new JsPluginLoader.JsPluginLoaderFactory());
    }

    protected Map<String, PluginDescriptor> descriptors;
    protected Map<String, PluginLoader> loaders;

    @Override
    public void loadPlugins(PluginLoadOrder order) {
        if (descriptors == null) {
            descriptors = new HashMap<>();
            loaders = new HashMap<>();

            // 1. Load possible plugin paths from plugin sources
            Set<Path> paths = findPluginPaths();

            // 2. Find and use plugin loader to load plugin descriptor for each plugin path
            findLoadersAndLoadDescriptors(paths, descriptors, loaders);

            // 3. Check for circular dependencies
            checkCircularDependencies(descriptors);
        }

        // 4. Load plugins (parent -> child)
        onLoad(descriptors, loaders, order);
    }

    protected void findLoadersAndLoadDescriptors(Set<Path> paths, Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> loaders) {
        for (var path : paths) {
            var loader = findLoader(path);
            if (loader == null) continue;
            var descriptor = loader.loadDescriptor();
            var name = descriptor.getName();
            if (descriptors.containsKey(name)) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DUPLICATE, name));
                continue;
            }
            descriptors.put(name, descriptor);
            loaders.put(name, loader);
        }
    }

    protected void onLoad(Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> loaders, PluginLoadOrder order) {
        Iterator<String> iterator = pluginsSortedList.iterator();
        start:
        while (iterator.hasNext()) {
            var s = iterator.next();
            var descriptor = descriptors.get(s);
            if (descriptor.getOrder() != order) continue;
            var loader = loaders.get(s);
            for (var e : descriptor.getDependencies()) {
                if (!plugins.containsKey(e.name())) {
                    if (!e.optional()) {
                        log.error(I18n.get().tr(TrKeys.A_PLUGIN_DEPENDENCY_MISSING, descriptor.getName(), e.name()));
                        iterator.remove();
                        continue start;
                    }
                }
            }
            PluginContainer pluginContainer;
            try {
                pluginContainer = loader.loadPlugin();
            } catch (Exception e) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_LOAD_ERROR, descriptor.getName()), e);
                continue;
            }
            plugins.put(descriptor.getName(), pluginContainer);
            pluginContainer.plugin().onLoad();
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_LOADING, descriptor.getName()));
        }
    }

    protected void checkCircularDependencies(Map<String, ? extends PluginDescriptor> descriptors) {
        // Add all plugin names to dag firstly
        dag.addAll(descriptors.keySet());
        for (var descriptor : descriptors.values()) {
            for (var dependency : descriptor.getDependencies()) {
                String name = dependency.name();
                //add dependency plugin to DAG
                dag.add(name);
                try {
                    dag.setBefore(name, descriptor.getName());//set ref
                } catch (DAGCycleException e) {
                    log.error("Circular dependencies appear in plugin {}: " + e.getMessage() + "The plugin will skip loading!", descriptor.getName());
                    dag.remove(descriptor.getName());
                }
            }
        }
        pluginsSortedList = dag.getSortedList();
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
        for (var s : pluginsSortedList) {
            var pluginContainer = getPlugin(s);
            if (isPluginEnabled(pluginContainer.descriptor().getName())) return;
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_ENABLING, pluginContainer.descriptor().getName()));
            try {
                var plugin = pluginContainer.plugin();
                plugin.onEnable();
                plugin.setEnabled(true);
            } catch (Exception e) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_ENABLE_ERROR, pluginContainer.descriptor().getName()), e);
            }
            enabledPlugins.put(pluginContainer.descriptor().getName(), pluginContainer);
        }
    }

    @Override
    public void disablePlugins() {
        for (var s : pluginsSortedList) {
            var pluginContainer = getPlugin(s);
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_DISABLING, pluginContainer.descriptor().getName()));
            try {
                var plugin = pluginContainer.plugin();
                plugin.onDisable();
                plugin.setEnabled(false);
            } catch (Exception e) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DISABLE_ERROR, pluginContainer.descriptor().getName()), e);
            }
        }
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

    @Override
    public Map<String, PluginContainer> getEnabledPlugins() {
        return Collections.unmodifiableMap(enabledPlugins);
    }

    @Override
    public boolean isPluginEnabled(String name) {
        return enabledPlugins.containsKey(name);
    }

    protected PluginLoader findLoader(Path pluginPath) {
        for (var loaderFactory : loaderFactories) {
            if (loaderFactory.canLoad(pluginPath)) return loaderFactory.create(pluginPath);
        }
        return null;
    }
}
