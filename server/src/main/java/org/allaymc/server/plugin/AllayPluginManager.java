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
    protected DirectedAcyclicGraph<String> dag;
    protected List<String> sortedPluginList;

    protected State state;

    public AllayPluginManager() {
        this.sources = new HashSet<>();
        this.customSources = new HashSet<>();
        this.loaderFactories = new HashSet<>();
        this.customLoaderFactories = new HashSet<>();
        this.plugins = new HashMap<>();
        this.enabledPlugins = new HashMap<>();
        this.dag = new HashDirectedAcyclicGraph<>();
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

        // 1. Load possible plugin paths from plugin sources
        var paths = findPluginPaths(this.sources);
        var descriptors = new HashMap<String, PluginDescriptor>();
        var loaders = new HashMap<String, PluginLoader>();
        // 2. Find and use plugin loader to load plugin descriptor for each plugin path
        findLoadersAndLoadDescriptors(paths, descriptors, loaders);
        // 3. Check for circular dependencies
        checkCircularDependencies(descriptors);
        // 4. Load plugins (parent -> child)
        onLoad(descriptors, loaders);

        // 5. Load plugins through custom sources and loaders
        // Plugins may add custom sources and loaders during
        // their onLoad() method
        loadPluginsCustom();

        this.state = State.LOADED;
    }

    protected Set<Path> findPluginPaths(Set<PluginSource> sources) {
        return sources.stream().flatMap(source -> source.find().stream()).collect(Collectors.toSet());
    }

    protected void findLoadersAndLoadDescriptors(Set<Path> paths, Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> loaders) {
        for (var path : paths) {
            var loader = findLoader(path);
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
            if (descriptors.containsKey(name)) {
                log.error(I18n.get().tr(TrKeys.A_PLUGIN_DUPLICATE, name));
                continue;
            }

            descriptors.put(name, descriptor);
            loaders.put(name, loader);
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
                    log.error(I18n.get().tr(TrKeys.A_PLUGIN_DEPENDENCY_CIRCULAR, descriptor.getName(), e.getMessage() != null ? e.getMessage() : ""));
                    dag.remove(descriptor.getName());
                }
            }
        }

        sortedPluginList = dag.getSortedList();
    }

    protected void onLoad(Map<String, PluginDescriptor> descriptors, Map<String, PluginLoader> loaders) {
        var iterator = sortedPluginList.iterator();
        start:
        while (iterator.hasNext()) {
            var str = iterator.next();
            var descriptor = descriptors.get(str);
            if (descriptor == null) {
                continue;
            }

            var loader = loaders.get(str);
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

    protected void loadPluginsCustom() {
        var paths = findPluginPaths(this.customSources);
        var descriptors = new HashMap<String, PluginDescriptor>();
        var loaders = new HashMap<String, PluginLoader>();
        findLoadersAndLoadDescriptors(paths, descriptors, loaders);
        checkCircularDependencies(descriptors);
        onLoad(descriptors, loaders);
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

    protected PluginLoader findLoader(Path pluginPath) {
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
