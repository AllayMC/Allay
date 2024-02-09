package org.allaymc.server.plugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.dag.CycleFoundException;
import org.allaymc.api.datastruct.dag.DAG;
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

    protected Set<PluginSource> sources = new HashSet<>();
    protected Set<PluginLoader.PluginLoaderFactory> loaderFactories = new HashSet<>();
    protected Map<String, PluginContainer> plugins = new HashMap<>();
    protected DAG<String> dag = new DAG<>();
    protected Map<String, PluginContainer> enabledPlugins = new HashMap<>();

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
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DUPLICATE, name));
                continue;
            }
            descriptors.put(name, descriptor);
            loaders.put(name, loader);
        }
    }

    protected void onLoad(Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> loaders) {
        dag.visit(node -> {
            var descriptor = descriptors.get(node.getObject());
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_LOADING, descriptor.getName()));
            var loader = loaders.get(node.getObject());
            PluginContainer pluginContainer;
            try {
                pluginContainer = loader.loadPlugin();
            } catch (Exception e) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_LOAD_ERROR, descriptor.getName()), e);
                return;
            }
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
                            throw new PluginException(I18n.get().tr(TrKeys.A_PLUGIN_DEPENDENCY_MISSING, descriptor.getName(), dependency.name()));
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
            var pluginContainer = getPlugin(node.getObject());
            if (isPluginEnabled(pluginContainer.descriptor().getName())) return;
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_ENABLING, pluginContainer.descriptor().getName()));
            try {
                pluginContainer.plugin().onEnable();
            } catch (Exception e) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_ENABLE_ERROR, pluginContainer.descriptor().getName()), e);
            }
            enabledPlugins.put(pluginContainer.descriptor().getName(), pluginContainer);
        });
    }

    @Override
    public void disablePlugins() {
        dag.visit(node -> {
            var pluginContainer = getPlugin(node.getObject());
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_DISABLING, pluginContainer.descriptor().getName()));
            try {
                pluginContainer.plugin().onDisable();
            } catch (Exception e) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DISABLE_ERROR, pluginContainer.descriptor().getName()), e);
            }
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
