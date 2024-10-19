package org.allaymc.server.plugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.plugin.*;
import org.allaymc.server.datastruct.dag.DAGCycleException;
import org.allaymc.server.datastruct.dag.HashDirectedAcyclicGraph;
import org.allaymc.server.plugin.jar.JarPluginLoader;
import org.semver4j.RangesListFactory;
import org.semver4j.Semver;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayPluginManager implements PluginManager {

    protected static Set<PluginSource> sources = new HashSet<>();
    protected static Set<PluginLoader.PluginLoaderFactory> loaderFactories = new HashSet<>();

    protected Map<String, PluginContainer> plugins = new HashMap<>();
    protected HashDirectedAcyclicGraph<String> dag = new HashDirectedAcyclicGraph<>();
    protected Map<String, PluginContainer> enabledPlugins = new HashMap<>();
    protected List<String> pluginsSortedList;
    protected Map<String, PluginDescriptor> descriptors;
    protected Map<String, PluginLoader> loaders;

    public AllayPluginManager() {
        registerSource(new DefaultPluginSource());
        registerLoaderFactory(new JarPluginLoader.JarPluginLoaderFactory());
    }

    public static void registerLoaderFactory(PluginLoader.PluginLoaderFactory loaderFactory) {
        loaderFactories.add(loaderFactory);
    }

    public static void registerSource(PluginSource pluginSource) {
        sources.add(pluginSource);
    }

    public void loadPlugins() {
        if (descriptors == null) {
            descriptors = new HashMap<>();
            loaders = new HashMap<>();

            // 1. Load possible plugin paths from plugin sources
            var paths = findPluginPaths();

            // 2. Find and use plugin loader to load plugin descriptor for each plugin path
            findLoadersAndLoadDescriptors(paths, descriptors, loaders);

            // 3. Check for circular dependencies
            checkCircularDependencies(descriptors);
        }

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
        var iterator = pluginsSortedList.iterator();
        start:
        while (iterator.hasNext()) {
            var str = iterator.next();
            var descriptor = descriptors.get(str);
            if (descriptor == null) continue;

            var loader = loaders.get(str);
            for (var dependency : descriptor.getDependencies()) {
                var dependencyContainer = plugins.get(dependency.name());

                if (dependencyContainer == null) {
                    if (dependency.optional()) continue start;

                    log.error(I18n.get().tr(TrKeys.A_PLUGIN_DEPENDENCY_MISSING, descriptor.getName(), dependency.name()));
                    iterator.remove();
                    continue start;
                }

                if (isUnexpectedDependencyVersion(dependencyContainer.descriptor(), dependency)) {
                    log.warn(I18n.get().tr(TrKeys.A_PLUGIN_DEPENDENCY_VERSION_MISMATCH,
                            descriptor.getName(),
                            dependency.name(),
                            RangesListFactory.create(dependency.version()),
                            dependencyContainer.descriptor().getVersion()));
                }
            }

            log.info(I18n.get().tr(TrKeys.A_PLUGIN_LOADING, descriptor.getName()));

            PluginContainer pluginContainer;
            try {
                pluginContainer = loader.loadPlugin();
                pluginContainer.plugin().onLoad();
            } catch (Exception e) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_LOAD_ERROR, descriptor.getName(), e.getMessage()), e);
                continue;
            }

            plugins.put(descriptor.getName(), pluginContainer);
        }
    }

    protected void checkCircularDependencies(Map<String, ? extends PluginDescriptor> descriptors) {
        // Add all plugin names to dag firstly
        dag.addAll(descriptors.keySet());
        for (var descriptor : descriptors.values()) {
            for (var dependency : descriptor.getDependencies()) {
                var name = dependency.name();
                // add dependency plugin to DAG
                dag.add(name);
                try {
                    dag.setBefore(name, descriptor.getName());//set ref
                } catch (DAGCycleException e) {
                    log.error(I18n.get().tr(TrKeys.A_PLUGIN_DEPENDENCY_CIRCULAR, descriptor.getName(), e.getMessage()));
                    dag.remove(descriptor.getName());
                }
            }
        }

        pluginsSortedList = dag.getSortedList();
    }

    protected boolean isUnexpectedDependencyVersion(PluginDescriptor dependency, PluginDependency requirement) {
        var requireVersion = requirement.version();
        if (requireVersion == null || requireVersion.isBlank()) return false;

        var dependencyVersion = dependency.getVersion();
        var dependencySemver = Semver.coerce(dependencyVersion);
        var versionRanges = RangesListFactory.create(requireVersion);
        assert dependencySemver != null;  // already checked at org.allaymc.api.plugin.PluginDescriptor.checkDescriptorValid
        return !dependencySemver.satisfies(versionRanges);
    }

    protected Set<Path> findPluginPaths() {
        return sources.stream().flatMap(source -> source.find().stream()).collect(Collectors.toSet());
    }

    public void enablePlugins() {
        for (var s : pluginsSortedList) {
            var pluginContainer = getPlugin(s);
            if (pluginContainer == null) {
                // Plugin failed to be loaded
                continue;
            }
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_ENABLING, pluginContainer.descriptor().getName()));
            try {
                var plugin = pluginContainer.plugin();
                plugin.onEnable();
            } catch (Throwable t) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_ENABLE_ERROR, pluginContainer.descriptor().getName(), t.getMessage()), t);
                continue;
            }

            enabledPlugins.put(pluginContainer.descriptor().getName(), pluginContainer);
        }
    }

    public void disablePlugins() {
        for (var s : pluginsSortedList.reversed()) {
            if (!isPluginEnabled(s)) continue;

            var pluginContainer = getPlugin(s);
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_DISABLING, pluginContainer.descriptor().getName()));
            try {
                var plugin = pluginContainer.plugin();
                plugin.onDisable();
            } catch (Throwable t) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DISABLE_ERROR, pluginContainer.descriptor().getName()), t);
            }
        }
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
        return loaderFactories.stream()
                .filter(loaderFactory -> loaderFactory.canLoad(pluginPath))
                .findFirst()
                .map(loaderFactory -> loaderFactory.create(pluginPath))
                .orElse(null);
    }
}
