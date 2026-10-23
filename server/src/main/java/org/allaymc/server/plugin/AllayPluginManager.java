package org.allaymc.server.plugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.plugin.PluginDisableEvent;
import org.allaymc.api.eventbus.event.plugin.PluginEnableEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.plugin.*;
import org.allaymc.server.datastruct.dag.DAGCycleException;
import org.allaymc.server.datastruct.dag.DirectedAcyclicGraph;
import org.allaymc.server.datastruct.dag.HashDirectedAcyclicGraph;
import org.allaymc.server.plugin.jar.JarPluginLoader;
import org.allaymc.server.utils.GitProperties;
import org.semver4j.Semver;
import org.semver4j.range.RangeListFactory;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * {@code AllayPluginManager} discovers plugins, orders dependency loading, and
 * invokes lifecycle callbacks.
 * {@code sources} and {@code loaderFactories} drive default discovery. Its
 * plugins can register {@code customSources} and {@code customLoaderFactories}
 * through {@code Plugin.onLoad}; custom discovery combines their candidates
 * with earlier paths while using only the custom factories. Both passes share
 * a dependency graph and publish containers
 * only after their load callbacks return, allowing later plugins to inspect
 * successfully loaded dependencies.
 *
 * <p>
 * Optional dependencies still affect order and version checks when present.
 * Missing required dependencies or version mismatches remove a candidate from
 * the current order, while callback failures are logged without rollback.
 * Enable callbacks run in that order and disable callbacks in reverse. The
 * enabled index records successful enablement and is retained after disabling;
 * callers must not use it as evidence that shutdown has not occurred. Lifecycle
 * operations and registration require externally serialized access.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayPluginManager implements PluginManager {

    /** Default-pass discovery sources. */
    protected Set<PluginSource> sources;
    /** Custom-pass discovery sources. */
    protected Set<PluginSource> customSources;
    /** Default-pass loader factories. */
    protected Set<PluginLoader.Factory> loaderFactories;
    /** Custom-pass loader factories. */
    protected Set<PluginLoader.Factory> customLoaderFactories;

    /**
     * {@code plugins} indexes successfully loaded containers by descriptor
     * name. Custom-pass publication can replace a same-name container from the
     * default pass.
     */
    protected Map<String, PluginContainer> plugins;
    /**
     * {@code enabledPlugins} retains containers whose enable callbacks returned
     * successfully. Disable callbacks do not remove these entries or change
     * their lookup results.
     */
    protected Map<String, PluginContainer> enabledPlugins;
    /**
     * {@code sortedPluginList} holds the latest dependency order, including
     * placeholder names without descriptors. Dependency rejection during
     * {@code onLoad} removes candidates from this list.
     */
    protected List<String> sortedPluginList;

    /** Current lifecycle gate. */
    protected State state;

    /**
     * Creates an unloaded manager with default folder discovery and JAR loading.
     * Constructing its default source may create the plugins directory; no
     * plugin descriptors or instances are loaded until {@code loadPlugins} runs.
     */
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

    /**
     * Adds a discovery source for the default loading pass.
     * Registration is accepted through {@code LOADED} and rejected afterward with
     * {@code IllegalStateException}; adding a source does not rerun a completed pass.
     */
    protected void registerSource(PluginSource pluginSource) {
        this.state.ensureNotBiggerThan(State.LOADED);
        this.sources.add(pluginSource);
    }

    /**
     * Adds a discovery source for the single custom loading pass.
     * The lifecycle gate permits registration through {@code LOADED}, but sources
     * registered after that pass are not automatically visited again.
     */
    @Override
    public void registerCustomSource(PluginSource customPluginSource) {
        this.state.ensureNotBiggerThan(State.LOADED);
        this.customSources.add(customPluginSource);
    }

    /**
     * Adds a loader factory to the default discovery pass.
     * Registration after {@code LOADED} throws {@code IllegalStateException}; factory iteration
     * follows the backing set and gives no priority guarantee between matches.
     */
    protected void registerLoaderFactory(PluginLoader.Factory loaderFactory) {
        this.state.ensureNotBiggerThan(State.LOADED);
        this.loaderFactories.add(loaderFactory);
    }

    /**
     * Adds a factory used exclusively by the custom discovery pass.
     * Registration is accepted through {@code LOADED} and rejected afterward; a factory
     * may match earlier default-source paths as well as custom-source paths.
     */
    @Override
    public void registerCustomLoaderFactory(PluginLoader.Factory customLoaderFactory) {
        this.state.ensureNotBiggerThan(State.LOADED);
        this.customLoaderFactories.add(customLoaderFactory);
    }

    /**
     * Performs default and custom discovery before advancing from {@code NEW} to {@code LOADED}.
     * The first pass loads descriptors, builds dependency order, and invokes
     * successful plugin loads so their callbacks can register custom discovery.
     * The custom pass reuses the graph and earlier paths with its own factories.
     * Individual descriptor and callback failures are logged where handled;
     * other failures propagate, leaving any already published plugins and the
     * current lifecycle state unchanged rather than rolling the pass back.
     */
    public void loadPlugins() {
        this.state.ensureEquals(State.NEW);

        var dag = new HashDirectedAcyclicGraph<String>();
        var foundPaths = findPluginPaths(this.sources);
        var foundDescriptors = new HashMap<String, PluginDescriptor>();
        var foundLoaders = new HashMap<String, PluginLoader>();
        findLoadersAndLoadDescriptors(foundPaths, foundDescriptors, foundLoaders, this.loaderFactories);
        calculateLoadingOrder(dag, foundDescriptors);
        onLoad(foundDescriptors, foundLoaders);

        // Default plugins must finish onLoad before custom discovery reads the
        // sources and factories those callbacks have registered for the second pass.
        loadPluginsCustom(dag, foundPaths);

        this.state = State.LOADED;
    }

    /**
     * Collects candidate paths from all supplied sources into a new set.
     * Equal paths are deduplicated; sources must return non-null sets, and
     * source failures propagate without converting discovery to an empty result.
     */
    protected Set<Path> findPluginPaths(Set<PluginSource> sources) {
        return sources.stream().flatMap(source -> source.find().stream()).collect(Collectors.toSet());
    }

    /**
     * Populates matching descriptor and loader maps for accepted plugin paths.
     * The first matching factory creates each loader; unmatched paths are
     * ignored. Descriptor-loading failures are logged and skipped, duplicate
     * names keep their first accepted entry in these maps, and incompatible
     * nonblank API ranges are rejected. The two maps are updated together
     * after those checks, but previously accepted candidates remain if factory
     * creation or a later metadata/range check throws outside the load catch.
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
                // A malformed candidate must not hide other discoverable plugins;
                // retain earlier accepted entries and skip only this descriptor-loading failure.
                log.error(I18n.get().tr(TrKeys.ALLAY_PLUGIN_DESCRIPTOR_ERROR, path.getFileName(), t.getMessage() != null ? t.getMessage() : ""), t);
                continue;
            }

            var name = descriptor.getName();
            if (foundDescriptors.containsKey(name)) {
                log.error(I18n.get().tr(TrKeys.ALLAY_PLUGIN_DUPLICATE, name));
                continue;
            }

            if (!descriptor.getAPIVersion().isBlank()) {
                // Compare development builds against the underlying API version;
                // removing the development marker keeps it out of the requested range comparison.
                var apiVersion = Semver.coerce(GitProperties.getBuildApiVersion().replace("-dev", ""));
                var apiVersionRange = RangeListFactory.create(descriptor.getAPIVersion());
                // noinspection all
                if (!apiVersion.satisfies(apiVersionRange)) {
                    log.error(I18n.get().tr(TrKeys.ALLAY_PLUGIN_API_VERSION_MISMATCH, apiVersion, name, apiVersionRange));
                    continue;
                }
            }

            foundDescriptors.put(name, descriptor);
            foundLoaders.put(name, loader);
        }
    }

    /**
     * Extends the dependency graph and replaces {@code sortedPluginList} after
     * sorting. Required and optional dependency names become vertices even when
     * they lack descriptors, so the resulting order can include placeholder names
     * that {@code onLoad} skips.
     *
     * <p>
     * A reported cycle removes the dependent vertex and its existing edges.
     * Processing continues through the remaining dependencies; an edge targeting
     * that removed vertex can then throw {@code IllegalArgumentException}. Earlier
     * graph updates remain, and sorting occurs only if traversal finishes.
     */
    protected void calculateLoadingOrder(DirectedAcyclicGraph<String> dag, Map<String, ? extends PluginDescriptor> descriptors) {
        dag.addAll(descriptors.keySet());
        for (var descriptor : descriptors.values()) {
            for (var dependency : descriptor.getDependencies()) {
                var name = dependency.name();
                dag.add(name);
                try {
                    dag.setBefore(name, descriptor.getName());
                } catch (DAGCycleException e) {
                    // Remove this dependent and its earlier edges after graph
                    // cycle rejection. Remaining dependencies still target this descriptor
                    // and can fail if they encounter its now-absent vertex.
                    log.error(I18n.get().tr(TrKeys.ALLAY_PLUGIN_DEPENDENCY_CIRCULAR, descriptor.getName(), e.getMessage() != null ? e.getMessage() : ""));
                    dag.remove(descriptor.getName());
                }
            }
        }

        this.sortedPluginList = dag.getSortedList();
    }

    /**
     * Loads this pass's descriptors in the previously calculated dependency order.
     * Missing descriptors represent other-pass or placeholder vertices and are
     * skipped. Missing required dependencies and mismatched installed versions
     * remove the candidate from the order; absent optional dependencies do not.
     * A container is published only after {@code loadPlugin} and {@code Plugin.onLoad}
     * both return,
     * so dependents see completed loads. Callback failures leave earlier
     * publications intact and are logged without undoing loader side effects.
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
            // Dependency rejection removes this candidate from later lifecycle
            // traversal; already loaded dependencies remain published for other candidates.
            for (var dependency : descriptor.getDependencies()) {
                var dependencyContainer = plugins.get(dependency.name());

                if (dependencyContainer == null) {
                    if (dependency.optional()) {
                        continue;
                    }

                    log.error(I18n.get().tr(TrKeys.ALLAY_PLUGIN_DEPENDENCY_MISSING, descriptor.getName(), dependency.name()));
                    iterator.remove();
                    continue start;
                }

                if (isUnexpectedDependencyVersion(dependencyContainer.descriptor(), dependency)) {
                    log.error(I18n.get().tr(
                            TrKeys.ALLAY_PLUGIN_DEPENDENCY_VERSION_MISMATCH, descriptor.getName(), dependency.name(),
                            dependency.version(), dependencyContainer.descriptor().getVersion()
                    ));
                    iterator.remove();
                    continue start;
                }
            }

            log.info(I18n.get().tr(TrKeys.ALLAY_PLUGIN_LOADING, descriptor.getName()));
            PluginContainer pluginContainer;
            try {
                pluginContainer = loader.loadPlugin();
                pluginContainer.plugin().onLoad();
            } catch (Throwable t) {
                // Do not publish a container whose load callback failed;
                // previously loaded dependencies and any loader side effects remain available.
                log.error(I18n.get().tr(TrKeys.ALLAY_PLUGIN_LOAD_ERROR, descriptor.getName(), t.getMessage() != null ? t.getMessage() : ""), t);
                continue;
            }

            plugins.put(descriptor.getName(), pluginContainer);
        }
    }

    /**
     * Loads a custom pass using newly discovered paths plus earlier candidates.
     * Only custom factories participate, while dependency order extends the
     * existing graph and dependency availability uses the manager's loaded map.
     * The descriptor and loader maps are fresh, so duplicate-name rejection
     * applies within this pass rather than across both passes. A custom factory
     * can therefore reload an earlier path or replace a loaded same-name plugin;
     * provider registration does not imply automatic duplicate-load prevention.
     */
    protected void loadPluginsCustom(DirectedAcyclicGraph<String> dag, Set<Path> existingPaths) {
        var paths = findPluginPaths(this.customSources);
        paths.addAll(existingPaths);
        var foundDescriptors = new HashMap<String, PluginDescriptor>();
        var foundLoaders = new HashMap<String, PluginLoader>();
        // Revisit earlier paths so custom factories can recognize formats that
        // default factories missed; this also allows them to recognize already loaded paths.
        findLoadersAndLoadDescriptors(paths, foundDescriptors, foundLoaders, this.customLoaderFactories);
        calculateLoadingOrder(dag, foundDescriptors);
        onLoad(foundDescriptors, foundLoaders);
    }

    /**
     * Returns whether an installed dependency fails its requested version range.
     * A {@code null} value from {@code requirement.version()} bypasses version
     * parsing entirely; otherwise the
     * descriptor version is coerced and checked with the range library, whose
     * parsing or invalid-version failures propagate.
     */
    protected boolean isUnexpectedDependencyVersion(PluginDescriptor dependency, PluginDependency requirement) {
        var requireVersion = requirement.version();
        if (requireVersion == null) {
            return false;
        }

        var dependencyVersion = dependency.getVersion();
        var dependencySemver = Semver.coerce(dependencyVersion);
        var versionRanges = RangeListFactory.create(requireVersion);
        // noinspection all
        return !dependencySemver.satisfies(versionRanges);
    }

    /**
     * Invokes enable notifications and callbacks in dependency order from {@code LOADED}.
     * Missing loaded containers are skipped, and each container is indexed as
     * enabled only after its callback returns. A callback failure is logged
     * and does not roll back other enablements or prevent later dependents
     * from being attempted. The manager reaches {@code ENABLED} after traversal even
     * when some plugins failed, so the enabled index rather than the manager
     * state identifies which callbacks completed successfully.
     */
    public void enablePlugins() {
        this.state.ensureEquals(State.LOADED);

        for (var pluginName : sortedPluginList) {
            var pluginContainer = getPlugin(pluginName);
            if (pluginContainer == null) {
                continue;
            }

            log.info(I18n.get().tr(TrKeys.ALLAY_PLUGIN_ENABLING, pluginContainer.descriptor().getName()));
            try {
                var plugin = pluginContainer.plugin();
                new PluginEnableEvent(plugin).call();
                plugin.onEnable();
            } catch (Throwable t) {
                // A failed enable callback must not enter the enabled index;
                // continue attempting later plugins without rolling back earlier successful callbacks.
                log.error(I18n.get().tr(TrKeys.ALLAY_PLUGIN_ENABLE_ERROR, pluginContainer.descriptor().getName(), t.getMessage() != null ? t.getMessage() : ""), t);
                continue;
            }

            enabledPlugins.put(pluginContainer.descriptor().getName(), pluginContainer);
        }

        this.state = State.ENABLED;
    }

    /**
     * Invokes disable notifications and callbacks in reverse dependency order.
     * The manager must be {@code ENABLED}, and only names present in its enabled index
     * are considered. Callback failures are logged so later shutdown callbacks
     * still run; the index itself is retained unchanged, and the manager reaches
     * {@code DISABLED} after traversal regardless of those failures. Resources created
     * by plugins or their loaders are not automatically closed here, so each
     * plugin's callback remains responsible for its own cleanup.
     */
    public void disablePlugins() {
        this.state.ensureEquals(State.ENABLED);

        for (var pluginName : sortedPluginList.reversed()) {
            if (!isPluginEnabled(pluginName)) continue;

            var pluginContainer = getPlugin(pluginName);
            log.info(I18n.get().tr(TrKeys.ALLAY_PLUGIN_DISABLING, pluginContainer.descriptor().getName()));
            try {
                var plugin = pluginContainer.plugin();
                new PluginDisableEvent(plugin).call();
                plugin.onDisable();
            } catch (Throwable t) {
                // One plugin's failed cleanup must not prevent the remaining
                // reverse-order callbacks; the enabled index is retained even for failed cleanup.
                log.error(I18n.get().tr(TrKeys.ALLAY_PLUGIN_DISABLE_ERROR, pluginContainer.descriptor().getName()), t);
            }
        }

        this.state = State.DISABLED;
    }

    /**
     * Returns an unmodifiable live view of successfully loaded containers.
     * The view reflects later publications and replacements; neither its
     * containers nor the plugin instances they reference are copied.
     */
    @Override
    public Map<String, PluginContainer> getPlugins() {
        return Collections.unmodifiableMap(plugins);
    }

    /**
     * Returns the loaded container under the exact descriptor name.
     * Unknown names return {@code null}, including candidates whose loading callback
     * failed before publication into the manager's loaded index.
     */
    @Override
    public PluginContainer getPlugin(String name) {
        return plugins.get(name);
    }

    /**
     * Returns the container recorded after successful enablement, or {@code null} when
     * absent. Disable callbacks retain this entry, so a non-null result does
     * not establish that the manager is still running.
     */
    @Override
    public PluginContainer getEnabledPlugin(String name) {
        return enabledPlugins.get(name);
    }

    /**
     * Returns an unmodifiable live view of the successful-enablement index.
     * Containers remain shared, and shutdown retains the map's entries rather
     * than turning this view into an index of active callbacks.
     */
    @Override
    public Map<String, PluginContainer> getEnabledPlugins() {
        return Collections.unmodifiableMap(enabledPlugins);
    }

    /**
     * Returns whether this name appears in the successful-enablement index.
     * The lookup remains {@code true} after disable callbacks for previously enabled
     * names because shutdown does not remove entries from that index.
     */
    @Override
    public boolean isPluginEnabled(String name) {
        return enabledPlugins.containsKey(name);
    }

    /**
     * Creates a loader using the first accepting factory in set iteration order.
     * No match returns {@code null}. Matching and construction failures propagate;
     * competing factories receive no stable priority from the manager's {@code HashSet}.
     */
    protected PluginLoader findLoaderIn(Set<PluginLoader.Factory> loaderFactories, Path pluginPath) {
        return loaderFactories.stream()
                .filter(loaderFactory -> loaderFactory.canLoad(pluginPath))
                .findFirst()
                .map(loaderFactory -> loaderFactory.create(pluginPath))
                .orElse(null);
    }

    /**
     * {@code State} gates registration and the manager's sequential loading and
     * shutdown phases in enum declaration order.
     */
    protected enum State {
        /**
         * Newly constructed plugin manager.
         */
        NEW,
        /**
         * Completed plugin loading phase.
         */
        LOADED,
        /**
         * Completed plugin enablement traversal.
         */
        ENABLED,
        /**
         * {@code DISABLED} records that disable callbacks have been attempted.
         * Failures may leave plugin resources active, and successful-enablement
         * lookup entries remain retained.
         */
        DISABLED;

        /**
         * Rejects a lifecycle phase later than the supplied upper bound.
         * Enum declaration order defines the phase order, and a later current
         * phase produces {@code IllegalStateException} without changing either state.
         */
        public void ensureNotBiggerThan(State state) {
            if (this.ordinal() > state.ordinal()) {
                throw new IllegalStateException();
            }
        }

        /**
         * Requires the current lifecycle phase to match the supplied phase.
         * A mismatch throws {@code IllegalStateException} before the guarded operation
         * proceeds; this check does not advance or synchronize manager state.
         */
        public void ensureEquals(State state) {
            if (state != this) {
                throw new IllegalStateException();
            }
        }
    }
}
