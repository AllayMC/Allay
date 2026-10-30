package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.OkaeriConfigInitializer;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.world.WorldGeneratorInitEvent;
import org.allaymc.api.eventbus.event.world.WorldLoadEvent;
import org.allaymc.api.eventbus.event.world.WorldUnloadEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.world.dimension.BuiltinDimensionSettings;
import org.allaymc.server.world.dimension.DimensionId;
import org.allaymc.server.world.light.NoLightEngine;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code AllayWorldPool} manages loaded worlds and the configuration used to
 * reconstruct them at startup. Dimension identifiers are normalized and
 * validated before generators attach to dimension instances. A prepared world
 * enters the live map only after its load event is accepted, and unload
 * requests remove map membership after requesting shutdown rather than waiting
 * for resource cleanup. Callers serialize lifecycle mutations; the concurrent
 * map does not make the surrounding check, construction, event, and start
 * sequence atomic.
 *
 * <p>
 * Server shutdown requests every retained world to stop, waits for their
 * published {@code STOPPED} states, and then reconstructs startup settings
 * from non-runtime worlds. Each dimension's generator and lighting policy are
 * preserved in those settings, with the overworld emitted first. A world that
 * cannot publish {@code STOPPED} can keep this wait running indefinitely.
 * Configuration replacement and world cleanup have no common rollback, and
 * cancelled or failed loading does not dispose every object already
 * constructed.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayWorldPool implements WorldPool {

    /**
     * {@code worlds} holds loaded membership by stable name; concurrent lookup
     * is supported, but lifecycle operations spanning this map require caller
     * serialization.
     */
    private final Map<String, AllayWorld> worlds;
    /**
     * Base folder used for configured world storage.
     */
    @Getter
    private final Path worldFolder;
    /**
     * {@code worldConfig} retains normalized startup settings and is rewritten
     * from current persistent worlds only after shutdown waiting completes.
     */
    private final WorldSettings worldConfig;

    /**
     * {@code defaultWorld} caches an explicit or configured selection;
     * unloading other entries does not refresh a previously cached world
     * reference.
     */
    @Setter
    private World defaultWorld;

    /**
     * Creates an empty pool and loads its bound world settings file.
     * Construction normalizes configured identifiers, replaces built-in height
     * settings with the active snapshot, saves that configuration, and
     * publishes it to built-in dimension settings.
     */
    public AllayWorldPool() {
        this.worlds = new ConcurrentHashMap<>();
        this.worldFolder = Path.of("worlds");
        var builtinDimensionSettings = BuiltinDimensionSettings.get();
        this.worldConfig = Objects.requireNonNull(ConfigManager.create(
                WorldSettings.class,
                createWorldSettingsInitializer(this.worldFolder.resolve("world-settings.yml"))
        ));
        this.worldConfig.normalize();
        applyBuiltinDimensionSettings(builtinDimensionSettings);
        this.worldConfig.save();
        BuiltinDimensionSettings.useWorldSettings(this.worldConfig);
    }

    /**
     * Attempts each configured world load while logging and skipping ordinary
     * exceptions. A failure in one entry leaves previously loaded worlds
     * available; errors outside {@code Exception} are not handled by this
     * loop.
     */
    public void loadWorlds() {
        this.worldConfig.worlds().forEach((name, setting) -> {
            try {
                loadWorld(name, setting);
            } catch (Exception e) {
                // A configured world failure should leave earlier successful loads
                // available and allow later entries to be attempted; this boundary
                // records the failed name without rolling back those worlds.
                log.error("Error when loading world {}, skipped.", name, e);
            }
        });
    }

    /**
     * Requests shutdown of every currently retained world and waits for their
     * final states. Callers run this operation outside the world threads and
     * serialize it with pool mutation, because the spin wait requires those
     * threads to make progress. No timeout or failure recovery bounds the
     * wait.
     *
     * <p>
     * After all retained worlds publish {@code STOPPED}, the method replaces
     * configured world entries with settings for non-runtime worlds and saves
     * active built-in height settings. Cleanup failures that prevent final
     * publication also prevent configuration rewriting; a later configuration
     * save failure does not restart stopped worlds or restore cleared entries.
     */
    public void shutdown() {
        this.worlds.values().forEach(AllayWorld::shutdown);

        // World shutdown is asynchronous. Wait for each retained world's final
        // publication before rebuilding settings; an uncaught world cleanup failure
        // can leave this loop waiting because no timeout or failure state exists.
        while (this.worlds.values().stream().anyMatch(world -> world.getState() != WorldState.STOPPED)) {
            Thread.onSpinWait();
        }

        // Rebuild startup declarations after worlds stop so runtime-only instances
        // disappear from automatic loading while retained dimensions keep their
        // final generator and lighting selections in the saved configuration.
        this.worldConfig.worlds().clear();
        this.worlds.forEach((name, world) -> {
            if (!world.isRuntimeOnly()) {
                this.worldConfig.worlds().put(name, toWorldSetting(world));
            }
        });
        applyBuiltinDimensionSettings(BuiltinDimensionSettings.get());
        this.worldConfig.save();
    }

    /**
     * Validates dimension settings, constructs a world, and admits it after an
     * accepted load event. Callers serialize lifecycle changes; an existing
     * name is rejected, but the name check and later insertion are not one
     * atomic reservation. Construction failures are logged and return without
     * an entry.
     *
     * <p>
     * The overworld is required and attaches before other dimensions. Event
     * cancellation leaves the prepared world unregistered without an explicit
     * disposal path, while accepted dispatch inserts membership before
     * starting ticks. Later dimension or start failures can therefore leave
     * constructed resources or a registered world without completing the full
     * sequence.
     */
    @Override
    public void loadWorld(WorldSetting worldSetting, Map<Identifier, DimensionSetting> dimensionSettings) {
        var name = worldSetting.name();
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_LOADING, name));
        if (worlds.containsKey(name)) {
            throw new IllegalArgumentException("World " + name + " is already loaded");
        }

        var validatedDimensionSettings = new LinkedHashMap<Identifier, DimensionSetting>();
        for (var entry : dimensionSettings.entrySet()) {
            var identifier = entry.getKey();
            var dimensionSetting = Preconditions.checkNotNull(entry.getValue(), "Dimension setting for %s cannot be null", identifier);
            validateDimensionType(dimensionSetting.dimensionType());
            Preconditions.checkArgument(
                    identifier.equals(dimensionSetting.dimensionType().getIdentifier()),
                    "Dimension setting key %s does not match dimension type identifier %s",
                    identifier,
                    dimensionSetting.dimensionType().getIdentifier()
            );
            Preconditions.checkArgument(
                    validatedDimensionSettings.put(identifier, dimensionSetting) == null,
                    "Duplicate dimension setting for %s",
                    identifier
            );
        }

        var useVirtualThread = worldSetting.useVirtualThread();
        AllayWorld world;
        try {
            world = new AllayWorld(name, worldSetting.storage(), useVirtualThread);
        } catch (Throwable t) {
            // Construction failure prevents pool admission. Return after logging the
            // initialization error; objects allocated before the failure are not
            // disposed by this loader's recovery path.
            log.error("Error while initializing world {}", name, t);
            return;
        }

        var overworldSetting = Preconditions.checkNotNull(
                validatedDimensionSettings.get(DimensionTypes.OVERWORLD.getIdentifier()),
                "World must have the overworld dimension"
        );
        loadDimension(world, overworldSetting, useVirtualThread);
        for (var setting : validatedDimensionSettings.values()) {
            if (setting.dimensionType() == DimensionTypes.OVERWORLD) {
                continue;
            }
            loadDimension(world, setting, useVirtualThread);
        }

        var event = new WorldLoadEvent(world);
        if (!event.call()) {
            return;
        }

        // Publish membership before starting ticks so world lookups can resolve
        // the instance during its startup callbacks; a later start failure
        // does not remove the already published entry.
        this.worlds.put(world.getName(), world);
        world.startTick();
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_LOADED, name));
    }

    /**
     * Requests shutdown and removes a loaded nondefault world after an
     * accepted unload event. Missing names and the selected default are
     * rejected with {@code IllegalArgumentException}; cancellation leaves
     * membership unchanged, and successful removal does not wait for cleanup.
     */
    @Override
    public void unloadWorld(String name) {
        var world = this.worlds.get(name);
        if (world == null) {
            throw new IllegalArgumentException("World " + name + " not found");
        }
        if (world == getDefaultWorld()) {
            throw new IllegalArgumentException("Cannot unload default world");
        }

        var event = new WorldUnloadEvent(world);
        if (!event.call()) {
            return;
        }

        world.shutdown();
        this.worlds.remove(name);
    }

    /**
     * Returns the current loaded-world entry under the supplied stable name.
     * Missing membership produces {@code null} without loading storage or
     * preparing a world, and later pool changes can alter subsequent lookup
     * results.
     */
    @Override
    public World getWorld(String name) {
        return this.worlds.get(name);
    }

    /**
     * Returns an unmodifiable view backed by the live world map. Later load
     * and unload operations remain visible, while world instances are retained
     * by reference rather than copied.
     */
    @Override
    public Map<String, World> getWorlds() {
        return Collections.unmodifiableMap(this.worlds);
    }

    /**
     * Returns the cached default or resolves its configured name when no value
     * is cached. An absent entry returns {@code null} and permits a later
     * retry; a cached selection is not automatically refreshed.
     */
    @Override
    public World getDefaultWorld() {
        if (defaultWorld == null) defaultWorld = getWorld(worldConfig.defaultWorld());
        return defaultWorld;
    }

    /**
     * Constructs storage and generator settings for one configured world, then
     * delegates admission. A storage factory returning {@code null} selects
     * {@code LEVELDB}; an unregistered storage factory is dereferenced before
     * that fallback and can fail. Dimension types must already be registered.
     */
    private void loadWorld(String name, WorldSettings.WorldSetting setting) {
        var storage = Registries.WORLD_STORAGE_FACTORIES.get(setting.storageType()).apply(this.worldFolder.resolve(name));
        // Only a factory result of null reaches this fallback. A missing factory
        // fails at the preceding invocation before LEVELDB can be selected.
        if (storage == null) {
            log.error("Cannot find world storage type {}", setting.storageType());
            storage = Registries.WORLD_STORAGE_FACTORIES.get("LEVELDB").apply(this.worldFolder.resolve(name));
        }

        var dimensionSettings = new LinkedHashMap<Identifier, DimensionSetting>();
        for (var entry : setting.normalizedDimensions().entrySet()) {
            var dimensionType = Registries.DIMENSIONS.getByK2(entry.getKey());
            Preconditions.checkArgument(dimensionType != null, "Dimension type %s is not registered", entry.getKey());
            dimensionSettings.put(
                    entry.getKey(),
                    new DimensionSetting(
                            dimensionType,
                            tryCreateWorldGenerator(entry.getValue()),
                            entry.getValue().enableLightCalculation()
                    )
            );
        }

        loadWorld(
                new WorldSetting(name, storage, setting.useVirtualThread),
                dimensionSettings
        );
    }

    /**
     * Creates a configured generator, falling back to {@code VOID} for an
     * unknown factory or a failed construction. Failures are logged; a failure
     * constructing the fallback itself can still propagate to the world
     * loader.
     */
    private WorldGenerator tryCreateWorldGenerator(WorldSettings.WorldSetting.DimensionSetting settings) {
        var factory = Registries.WORLD_GENERATOR_FACTORIES.get(settings.generatorType());
        // An unknown configured generator still permits an empty world pipeline.
        // Select VOID here, then apply the same preset construction boundary
        // used for known generator factories.
        if (factory == null) {
            log.error("Cannot find world generator {}, fallback to the VOID generator", settings.generatorType());
            factory = Registries.WORLD_GENERATOR_FACTORIES.get("VOID");
        }

        try {
            return factory.apply(settings.generatorPreset());
        } catch (Throwable t) {
            // A failed preset or factory abandons this generator proposal. Construct
            // VOID without a preset as recovery; failures from that recovery
            // remain visible to the enclosing world loader.
            log.error("Error while creating {} type world generator with the preset {}, fallback to the VOID generator", settings.generatorType(), settings.generatorPreset(), t);
            return Registries.WORLD_GENERATOR_FACTORIES.get("VOID").apply(null);
        }
    }

    /**
     * Dispatches the generator initialization event before creating and
     * attaching a dimension. Listeners configure the still-unattached
     * generator; construction then binds it to the new dimension and closes
     * that configuration phase.
     */
    private void loadDimension(AllayWorld world, DimensionSetting dimensionSetting, boolean useVirtualThread) {
        new WorldGeneratorInitEvent(world, dimensionSetting.worldGenerator(), dimensionSetting.dimensionType()).call();
        world.addDimension(new AllayDimension(
                world,
                dimensionSetting.worldGenerator(),
                dimensionSetting.dimensionType(),
                dimensionSetting.enableLightCalculation(),
                useVirtualThread
        ));
    }

    /**
     * Builds startup settings from a loaded world's storage, thread policy,
     * and dimensions. The overworld is emitted first, and each dimension
     * retains its registered identifier as the configuration key. Returned
     * setting objects refer to newly assembled mappings rather than the live
     * dimension map.
     *
     * <p>
     * Generator names and presets reconstruct generation choices, while the
     * actual engine type selects whether lighting remains enabled. This
     * conversion records reconstruction data rather than world contents, so
     * the preceding world shutdown must perform chunk, entity, and metadata
     * persistence separately.
     */
    private WorldSettings.WorldSetting toWorldSetting(World world) {
        var dimensions = new LinkedHashMap<String, WorldSettings.WorldSetting.DimensionSetting>();
        var overworld = world.getOverWorld();
        if (overworld != null) {
            dimensions.put(
                    DimensionId.OVERWORLD.getIdentifier().toString(),
                    toWorldDimensionSetting(overworld)
            );
        }
        for (var entry : world.getDimensions().entrySet()) {
            if (entry.getKey() == DimensionTypes.OVERWORLD) {
                continue;
            }
            dimensions.put(entry.getKey().getIdentifier().toString(), toWorldDimensionSetting(entry.getValue()));
        }

        return new WorldSettings.WorldSetting(
                world.getWorldStorage().getName(),
                world.isVirtualTickingThread(),
                dimensions
        );
    }

    /**
     * Returns reconstruction settings for a dimension's generator and lighting
     * policy. A {@code NoLightEngine} instance disables calculation in the
     * saved setting; other engine implementations preserve the enabled choice.
     */
    private WorldSettings.WorldSetting.DimensionSetting toWorldDimensionSetting(Dimension dimension) {
        var generator = dimension.getWorldGenerator();
        var lightEnabled = !(((AllayDimension) dimension).getLightEngine() instanceof NoLightEngine);
        return new WorldSettings.WorldSetting.DimensionSetting(generator.getName(), generator.getPreset(), lightEnabled);
    }

    /**
     * Rejects dimension metadata incompatible with the server's section
     * layout. Heights must lie between {@code -512} and {@code 512}, preserve
     * inclusive section boundaries, and agree with the reported section count;
     * violations throw {@code IllegalArgumentException}.
     */
    private static void validateDimensionType(DimensionType dimensionType) {
        // Sections use sixteen-block inclusive ranges. Validate alignment and metadata
        // together so chunk arrays and dimension coordinates address the same
        // sections before any dimension manager is constructed.
        var minHeight = dimensionType.getMinHeight();
        var maxHeight = dimensionType.getMaxHeight();
        Preconditions.checkArgument(minHeight >= -512 && minHeight <= 512, "Dimension min height %s is out of range", minHeight);
        Preconditions.checkArgument(maxHeight >= -512 && maxHeight <= 512, "Dimension max height %s is out of range", maxHeight);
        Preconditions.checkArgument(maxHeight >= minHeight, "Dimension max height %s is smaller than min height %s", maxHeight, minHeight);
        Preconditions.checkArgument((minHeight & 15) == 0, "Dimension min height %s must align to section boundaries", minHeight);
        Preconditions.checkArgument((maxHeight & 15) == 15, "Dimension max height %s must align to section boundaries", maxHeight);
        Preconditions.checkArgument(
                dimensionType.chunkSectionCount() == dimensionType.maxSectionY() - dimensionType.minSectionY() + 1,
                "Dimension %s has inconsistent section metadata",
                dimensionType.getIdentifier()
        );
    }

    /**
     * Returns the canonical identifier accepted for a configured dimension
     * key.
     */
    private static Identifier normalizeDimensionIdentifier(String rawIdentifier) {
        return DimensionId.normalizeConfigIdentifier(rawIdentifier);
    }

    /**
     * Creates the YAML binding initializer for the supplied settings path.
     * Initialization installs the configurer, removes orphaned settings,
     * writes defaults, and loads existing values using the configuration
     * library's file operations.
     */
    private static OkaeriConfigInitializer createWorldSettingsInitializer(Path path) {
        return it -> {
            it.withConfigurer(new eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer());
            it.withBindFile(path);
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load();
        };
    }

    /**
     * Replaces configured built-in height entries with a new active-settings
     * snapshot. Each built-in dimension uses its canonical identifier, and the
     * replacement keeps configuration output aligned with ranges already
     * selected for runtime use.
     */
    private void applyBuiltinDimensionSettings(BuiltinDimensionSettings builtinDimensionSettings) {
        var builtinDimensions = new LinkedHashMap<String, WorldSettings.BuiltinDimensionSetting>();
        for (var dimensionId : DimensionId.values()) {
            var range = builtinDimensionSettings.getHeightRange(dimensionId);
            builtinDimensions.put(
                    dimensionId.getIdentifier().toString(),
                    new WorldSettings.BuiltinDimensionSetting(range.minHeight(), range.maxHeight())
            );
        }
        this.worldConfig.builtinDimensions(builtinDimensions);
    }

    /**
     * {@code WorldSettings} holds YAML world declarations and built-in height
     * settings before runtime loading.
     */
    @Getter
    @Accessors(fluent = true)
    public static class WorldSettings extends OkaeriConfig {

        /**
         * {@code builtinDimensions} stores shared built-in ranges;
         * normalization canonicalizes aliases and rejects duplicate identities
         * before the pool writes active ranges back.
         */
        @Comment("Height settings shared by the built-in dimensions in all worlds")
        @Setter
        @CustomKey("built-in-dimensions")
        private Map<String, BuiltinDimensionSetting> builtinDimensions = new LinkedHashMap<>(Map.of(
                DimensionId.OVERWORLD.getIdentifier().toString(),
                new BuiltinDimensionSetting(
                        DimensionId.OVERWORLD.getDefaultMinHeight(),
                        DimensionId.OVERWORLD.getDefaultMaxHeight()
                ),
                DimensionId.NETHER.getIdentifier().toString(),
                new BuiltinDimensionSetting(
                        DimensionId.NETHER.getDefaultMinHeight(),
                        DimensionId.NETHER.getDefaultMaxHeight()
                ),
                DimensionId.THE_END.getIdentifier().toString(),
                new BuiltinDimensionSetting(
                        DimensionId.THE_END.getDefaultMinHeight(),
                        DimensionId.THE_END.getDefaultMaxHeight()
                )
        ));

        /**
         * {@code worlds} maps startup names to settings; {@code normalize}
         * replaces the supplied map with a mutable ordered map before shutdown
         * rewrites it.
         */
        @Setter
        @CustomKey("worlds")
        private Map<String, WorldSetting> worlds = Map.of("world", new WorldSetting(
                "LEVELDB",
                false,
                new LinkedHashMap<>(Map.of(
                        DimensionId.OVERWORLD.getIdentifier().toString(),
                        new WorldSetting.DimensionSetting("FLAT", "")
                ))
        ));

        /**
         * Configured world name used for initial player placement.
         */
        @Comment("The default world is the world that newly joined players will be in")
        @CustomKey("default-world")
        private String defaultWorld = "world";

        /**
         * Returns built-in height settings keyed by resolved built-in
         * identities. Missing or empty input produces an empty result, while
         * {@code null} entries and identifiers outside the built-in set are
         * rejected. The returned map retains each setting object rather than
         * copying its range.
         *
         * <p>
         * Alias normalization can map multiple supplied keys to the same
         * dimension. Duplicate detection rejects that collision instead of
         * silently overwriting one configured range, preserving an explicit
         * failure when distinct text keys identify the same built-in
         * dimension.
         */
        public Map<DimensionId, BuiltinDimensionSetting> normalizedBuiltinDimensions() {
            var normalized = new LinkedHashMap<DimensionId, BuiltinDimensionSetting>();
            if (builtinDimensions == null || builtinDimensions.isEmpty()) {
                return normalized;
            }

            builtinDimensions.forEach((identifier, setting) -> {
                Preconditions.checkNotNull(setting, "Built-in dimension setting %s cannot be null", identifier);
                var dimensionId = DimensionId.fromIdentifier(normalizeDimensionIdentifier(identifier));
                Preconditions.checkArgument(dimensionId != null, "Built-in dimension setting %s is not a built-in dimension", identifier);
                Preconditions.checkArgument(
                        normalized.put(dimensionId, setting) == null,
                        "Duplicate built-in dimension setting for %s",
                        dimensionId.getIdentifier()
                );
            });
            return normalized;
        }

        /**
         * Replaces configured maps with ordered mappings containing canonical
         * dimension identifiers. Built-in normalization is assigned first,
         * then each world setting is converted; a missing or empty world map
         * becomes an empty mutable map. Setting objects may still share their
         * dimension configuration values.
         *
         * <p>
         * Normalization rejects invalid entries rather than preserving an
         * ambiguous duplicate identity. Publication is sequential rather than
         * transactional: a later world-setting failure can occur after
         * built-in replacement, while the existing world map remains until its
         * whole replacement has been prepared.
         */
        public void normalize() {
            var normalizedBuiltinDimensions = new LinkedHashMap<String, BuiltinDimensionSetting>();
            normalizedBuiltinDimensions().forEach((dimensionId, setting1) -> normalizedBuiltinDimensions.put(dimensionId.getIdentifier().toString(), setting1));
            this.builtinDimensions = normalizedBuiltinDimensions;

            var normalizedWorlds = new LinkedHashMap<String, WorldSetting>();
            // Prepare the replacement before assigning worlds; invalid later entries
            // must not publish a partially filled world map, although built-in
            // settings have already been replaced by this point.
            if (worlds != null && !worlds.isEmpty()) {
                worlds.forEach((name, setting) -> {
                    Preconditions.checkNotNull(setting, "World setting %s cannot be null", name);
                    normalizedWorlds.put(name, setting.normalized());
                });
            }
            this.worlds = normalizedWorlds;
        }

        /**
         * {@code BuiltinDimensionSetting} stores inclusive vertical bounds
         * shared by a built-in dimension across worlds.
         */
        @Getter
        @Accessors(fluent = true)
        @AllArgsConstructor
        public static class BuiltinDimensionSetting extends OkaeriConfig {
            /**
             * Inclusive minimum block height for the dimension.
             */
            @CustomKey("min-height")
            private int minHeight;

            /**
             * Inclusive maximum block height for the dimension.
             */
            @CustomKey("max-height")
            private int maxHeight;
        }

        /**
         * {@code WorldSetting} represents one world's storage, thread choice,
         * and modern or legacy dimension configuration.
         */
        @Getter
        @Accessors(fluent = true)
        public static class WorldSetting extends OkaeriConfig {
            /**
             * Registry key selecting the world storage factory.
             */
            @CustomKey("storage-type")
            private String storageType;

            /**
             * Thread policy for the world and calculation workers.
             */
            @Comment("If set to true, virtual thread will be used as the ticking thread of the world and the dimensions in the world")
            @CustomKey("use-virtual-thread")
            private boolean useVirtualThread;

            /**
             * {@code dimensions} takes precedence when nonempty; otherwise
             * normalization reads the legacy built-in fields instead of
             * merging the two representations.
             */
            private Map<String, DimensionSetting> dimensions;

            // overworld, nether, and theEnd supply legacy dimension declarations only
            // when dimensions is absent or empty. Missing legacy values omit those
            // dimensions rather than inventing settings during normalization.
            /**
             * Optional legacy configuration for the overworld dimension.
             */
            private DimensionSetting overworld;

            /**
             * Optional legacy configuration for the nether dimension.
             */
            private DimensionSetting nether;

            /**
             * Optional legacy configuration for the end dimension.
             */
            @CustomKey("the-end")
            private DimensionSetting theEnd;

            /**
             * Creates a world setting retaining the supplied dimension map and
             * thread policy. This convenience constructor leaves all legacy
             * built-in fields {@code null}; normalization later validates
             * entries and canonicalizes map keys.
             */
            public WorldSetting(String storageType, boolean useVirtualThread, Map<String, DimensionSetting> dimensions) {
                this(storageType, useVirtualThread, dimensions, null, null, null);
            }

            /**
             * Creates a world setting retaining modern and legacy
             * configuration references. A nonempty {@code dimensions} map
             * takes precedence during normalization; otherwise the optional
             * built-in fields supply the dimension declarations.
             */
            public WorldSetting(
                    String storageType,
                    boolean useVirtualThread,
                    Map<String, DimensionSetting> dimensions,
                    DimensionSetting overworld,
                    DimensionSetting nether,
                    DimensionSetting theEnd
            ) {
                this.storageType = storageType;
                this.useVirtualThread = useVirtualThread;
                this.dimensions = dimensions;
                this.overworld = overworld;
                this.nether = nether;
                this.theEnd = theEnd;
            }

            /**
             * Returns canonical dimension settings from the selected
             * configuration representation. A nonempty {@code dimensions} map
             * takes precedence over every legacy built-in field, while an
             * absent or empty map enables the legacy fields. The result
             * retains dimension setting references.
             *
             * <p>
             * Modern keys are normalized before insertion, and duplicate
             * canonical identifiers are rejected rather than silently
             * replacing one supplied declaration. Invalid or {@code null}
             * modern entries fail without falling back to legacy data,
             * preserving the distinction between an absent modern
             * configuration and an invalid configured mapping.
             */
            public Map<Identifier, DimensionSetting> normalizedDimensions() {
                var normalized = new LinkedHashMap<Identifier, DimensionSetting>();
                // A configured modern map owns dimension selection as a whole.
                // Reject its invalid entries rather than falling through to legacy
                // settings and silently changing which dimensions load.
                if (dimensions != null && !dimensions.isEmpty()) {
                    dimensions.forEach((identifier, setting) -> {
                        Preconditions.checkNotNull(setting, "Dimension setting %s cannot be null", identifier);
                        var normalizedIdentifier = normalizeDimensionIdentifier(identifier);
                        Preconditions.checkArgument(
                                normalized.put(normalizedIdentifier, setting) == null,
                                "Duplicate dimension setting for %s",
                                normalizedIdentifier
                        );
                    });
                    return normalized;
                }

                if (overworld != null) {
                    normalized.put(DimensionId.OVERWORLD.getIdentifier(), overworld);
                }
                if (nether != null) {
                    normalized.put(DimensionId.NETHER.getIdentifier(), nether);
                }
                if (theEnd != null) {
                    normalized.put(DimensionId.THE_END.getIdentifier(), theEnd);
                }
                return normalized;
            }

            /**
             * Returns a world setting with canonical string keys and no legacy
             * declarations. The mapping is newly assembled from {@code
             * normalizedDimensions}, while its individual dimension setting
             * objects remain shared with this setting.
             */
            public WorldSetting normalized() {
                var normalizedDimensions = new LinkedHashMap<String, DimensionSetting>();
                normalizedDimensions().forEach((identifier, setting) -> normalizedDimensions.put(identifier.toString(), setting));
                return new WorldSetting(storageType, useVirtualThread, normalizedDimensions);
            }

            /**
             * {@code DimensionSetting} stores the generator factory, its
             * preset, and the lighting policy for one configured dimension.
             */
            @Getter
            @Accessors(fluent = true)
            @AllArgsConstructor
            public static class DimensionSetting extends OkaeriConfig {
                /**
                 * Registry key selecting the generator factory.
                 */
                @CustomKey("generator-type")
                private String generatorType;

                /**
                 * Preset text supplied to the generator factory.
                 */
                @CustomKey("generator-preset")
                private String generatorPreset;

                /**
                 * Whether this dimension computes light instead of using full
                 * illumination.
                 */
                @Comment("If set to false, light calculation will be disabled for this dimension")
                @Comment("All light queries will return max value (15)")
                @CustomKey("enable-light-calculation")
                private boolean enableLightCalculation;

                /**
                 * Creates generator settings with light calculation enabled by
                 * default. The factory name and preset are retained for later
                 * generator construction; this constructor does not resolve
                 * the factory or validate its preset.
                 */
                public DimensionSetting(String generatorType, String generatorPreset) {
                    this(generatorType, generatorPreset, true);
                }
            }
        }
    }
}
