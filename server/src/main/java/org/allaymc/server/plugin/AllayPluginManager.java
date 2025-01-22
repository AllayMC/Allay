package org.allaymc.server.plugin;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.plugin.*;
import org.allaymc.server.datastruct.dag.DAGCycleException;
import org.allaymc.server.datastruct.dag.DirectedAcyclicGraph;
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

    protected Set<PluginSource> sources;
    protected Set<PluginSource> customSources;
    protected Set<PluginLoader.Factory> loaderFactories;
    protected Set<PluginLoader.Factory> customLoaderFactories;

    protected Map<String, PluginContainer> plugins;
    protected Map<String, PluginContainer> enabledPlugins;
    protected List<String> sortedPluginList;

    protected State state;

    public AllayPluginManager() {
        this.sources = new HashSet<>();
        this.customSources = new HashSet<>();
        this.loaderFactories = new HashSet<>();
        this.customLoaderFactories = new HashSet<>();
        this.plugins = new HashMap<>();
        this.enabledPlugins = new HashMap<>();
        this.state = State.NEW;

        registerSource(new DefaultPluginSource());
        registerLoaderFactory(new JarPluginLoader.Factory());
    }

    protected void registerSource(PluginSource pluginSource) {
        this.state.ensureNotBiggerThan(State.LOADED);
        this.sources.add(pluginSource);
    }

    @Override
    public void registerCustomSource(PluginSource customPluginSource) {
        this.state.ensureNotBiggerThan(State.LOADED);
        this.customSources.add(customPluginSource);
    }

    protected void registerLoaderFactory(PluginLoader.Factory loaderFactory) {
        this.state.ensureNotBiggerThan(State.LOADED);
        this.loaderFactories.add(loaderFactory);
    }

    @Override
    public void registerCustomLoaderFactory(PluginLoader.Factory customLoaderFactory) {
        this.state.ensureNotBiggerThan(State.LOADED);
        this.customLoaderFactories.add(customLoaderFactory);
    }

    public void loadPlugins() {
        this.state.ensureEquals(State.NEW);

        // 1. Load possible plugin foundPaths from plugin sources
        var dag = new HashDirectedAcyclicGraph<String>();
        var foundPaths = findPluginPaths(this.sources);
        var foundDescriptors = new HashMap<String, PluginDescriptor>();
        var foundLoaders = new HashMap<String, PluginLoader>();
        // 2. Find and use plugin loader to load plugin descriptor for each plugin path
        findLoadersAndLoadDescriptors(foundPaths, foundDescriptors, foundLoaders, this.loaderFactories);
        // 3. Check for circular dependencies
        calculateLoadingOrder(dag, foundDescriptors);
        // 4. Load plugins (parent -> child)
        onLoad(foundDescriptors, foundLoaders);

        // 5. Load plugins through custom sources and loaders
        // Plugins may add custom sources and loaders during
        // their onLoad() method
        loadPluginsCustom(dag, foundPaths);

        this.state = State.LOADED;
    }

    /**
     * Find plugin paths from the given plugin sources.
     *
     * @param sources the plugin sources to find plugin paths from.
     *
     * @return a set of plugin paths found from the given plugin sources.
     */
    protected Set<Path> findPluginPaths(Set<PluginSource> sources) {
        return sources.stream().flatMap(source -> source.find().stream()).collect(Collectors.toSet());
    }

    /**
     * Try to find plugin loader for each plugin path. If found, try to load descriptor through the found plugin loader.
     *
     * @param foundPaths       the plugin paths to find loaders and load descriptors from.
     * @param foundDescriptors the map that to store the found plugin descriptors.
     * @param foundLoaders     the map that to store the found plugin loaders.
     * @param loaderFactories  the plugin loader factories to find loader from.
     */
    protected void findLoadersAndLoadDescriptors(
            Set<Path> foundPaths, Map<String, PluginDescriptor> foundDescriptors,
            Map<String, PluginLoader> foundLoaders, Set<PluginLoader.Factory> loaderFactories
    ) {
        for (var path : foundPaths) {
            var loader = findLoaderIn(loaderFactories, path);
            if (loader == null) {
                continue;
            }

            PluginDescriptor descriptor;
            try {
                descriptor = loader.loadDescriptor();
            } catch (Throwable t) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DESCRIPTOR_ERROR, path.getFileName(), t.getMessage() != null ? t.getMessage() : ""), t);
                continue;
            }

            var name = descriptor.getName();
            if (foundDescriptors.containsKey(name)) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DUPLICATE, name));
                continue;
            }

            foundDescriptors.put(name, descriptor);
            foundLoaders.put(name, loader);
        }
    }

    /**
     * Add given descriptors to the dag and calculate the loading order. Circular dependencies
     * will also be checked. By the end, {@link #sortedPluginList} will be updated.
     *
     * @param dag         the directed acyclic graph to store the plugin dependencies.
     * @param descriptors the plugin descriptors that should be added to the directed acyclic graph.
     */
    protected void calculateLoadingOrder(DirectedAcyclicGraph<String> dag, Map<String, ? extends PluginDescriptor> descriptors) {
        // Add all plugin names to dag firstly
        dag.addAll(descriptors.keySet());
        for (var descriptor : descriptors.values()) {
            for (var dependency : descriptor.getDependencies()) {
                var name = dependency.name();
                // add dependency plugin to DAG
                dag.add(name);
                try {
                    dag.setBefore(name, descriptor.getName());
                } catch (DAGCycleException e) {
                    log.error(I18n.get().tr(TrKeys.A_PLUGIN_DEPENDENCY_CIRCULAR, descriptor.getName(), e.getMessage() != null ? e.getMessage() : ""));
                    dag.remove(descriptor.getName());
                }
            }
        }

        this.sortedPluginList = dag.getSortedList();
    }

    /**
     * Load plugins based on the given descriptors and loaders.
     * <p>
     * The loading order is based on {@link #sortedPluginList}, so before calling this method, make sure to call
     * {@link #calculateLoadingOrder(DirectedAcyclicGraph, Map)}
     *
     * @param descriptors  the descriptors of the plugins that need to be loaded.
     * @param foundLoaders the loaders of the plugins that need to be loaded.
     */
    protected void onLoad(Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> foundLoaders) {
        var iterator = sortedPluginList.iterator();
        start:
        while (iterator.hasNext()) {
            var str = iterator.next();
            var descriptor = descriptors.get(str);
            if (descriptor == null) {
                continue;
            }

            var loader = foundLoaders.get(str);
            for (var dependency : descriptor.getDependencies()) {
                var dependencyContainer = plugins.get(dependency.name());

                if (dependencyContainer == null) {
                    if (dependency.optional()) {
                        continue start;
                    }

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
            } catch (Throwable t) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_LOAD_ERROR, descriptor.getName(), t.getMessage() != null ? t.getMessage() : ""), t);
                continue;
            }

            plugins.put(descriptor.getName(), pluginContainer);
        }
    }

    protected void loadPluginsCustom(DirectedAcyclicGraph<String> dag, Set<Path> existingPaths) {
        var paths = findPluginPaths(this.customSources);
        paths.addAll(existingPaths);
        var foundDescriptors = new HashMap<String, PluginDescriptor>();
        var foundLoaders = new HashMap<String, PluginLoader>();
        // findLoadersAndLoadDescriptors will only find loader in this.customLoaderFactories,
        // so already loaded plugin won't be loaded twice
        findLoadersAndLoadDescriptors(paths, foundDescriptors, foundLoaders, this.customLoaderFactories);
        calculateLoadingOrder(dag, foundDescriptors);
        onLoad(foundDescriptors, foundLoaders);
    }

    protected boolean isUnexpectedDependencyVersion(PluginDescriptor dependency, PluginDependency requirement) {
        var requireVersion = requirement.version();
        if (requireVersion == null || requireVersion.isBlank()) {
            return false;
        }

        var dependencyVersion = dependency.getVersion();
        var dependencySemver = Semver.coerce(dependencyVersion);
        var versionRanges = RangesListFactory.create(requireVersion);
        // Already checked at org.allaymc.api.plugin.PluginDescriptor.checkDescriptorValid
        Preconditions.checkNotNull(dependencySemver);
        return !dependencySemver.satisfies(versionRanges);
    }

    public void enablePlugins() {
        this.state.ensureEquals(State.LOADED);

        for (var pluginName : sortedPluginList) {
            var pluginContainer = getPlugin(pluginName);
            if (pluginContainer == null) {
                // Plugin failed to be loaded
                continue;
            }
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_ENABLING, pluginContainer.descriptor().getName()));
            try {
                var plugin = pluginContainer.plugin();
                plugin.onEnable();
            } catch (Throwable t) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_ENABLE_ERROR, pluginContainer.descriptor().getName(), t.getMessage() != null ? t.getMessage() : ""), t);
                continue;
            }

            enabledPlugins.put(pluginContainer.descriptor().getName(), pluginContainer);
        }

        this.state = State.ENABLED;
    }

    public void disablePlugins() {
        this.state.ensureEquals(State.ENABLED);

        for (var pluginName : sortedPluginList.reversed()) {
            if (!isPluginEnabled(pluginName)) continue;

            var pluginContainer = getPlugin(pluginName);
            log.info(I18n.get().tr(TrKeys.A_PLUGIN_DISABLING, pluginContainer.descriptor().getName()));
            try {
                var plugin = pluginContainer.plugin();
                plugin.onDisable();
            } catch (Throwable t) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DISABLE_ERROR, pluginContainer.descriptor().getName()), t);
            }
        }

        this.state = State.DISABLED;
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
    public PluginContainer getEnabledPlugin(String name) {
        return enabledPlugins.get(name);
    }

    @Override
    public Map<String, PluginContainer> getEnabledPlugins() {
        return Collections.unmodifiableMap(enabledPlugins);
    }

    @Override
    public boolean isPluginEnabled(String name) {
        return enabledPlugins.containsKey(name);
    }

    protected PluginLoader findLoaderIn(Set<PluginLoader.Factory> loaderFactories, Path pluginPath) {
        return loaderFactories.stream()
                .filter(loaderFactory -> loaderFactory.canLoad(pluginPath))
                .findFirst()
                .map(loaderFactory -> loaderFactory.create(pluginPath))
                .orElse(null);
    }

    protected enum State {
        /**
         * The manager is just created.
         */
        NEW,
        /**
         * Plugins have been loaded but not enabled.
         */
        LOADED,
        /**
         * Loaded plugins have been enabled if they can be enabled.
         */
        ENABLED,
        /**
         * Enabled plugins have been disabled.
         */
        DISABLED;

        /**
         * Ensure the state is not bigger than the given state.
         *
         * @param state the state to compare with.
         *
         * @throws IllegalStateException if the state is bigger than the given state.
         */
        public void ensureNotBiggerThan(State state) {
            if (this.ordinal() > state.ordinal()) {
                throw new IllegalStateException();
            }
        }

        /**
         * Ensure the state is equals to the given state.
         *
         * @param state the state to compare with.
         *
         * @throws IllegalStateException if the state is not equals to the given state.
         */
        public void ensureEquals(State state) {
            if (state != this) {
                throw new IllegalStateException();
            }
        }
    }
}
