package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
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
import org.allaymc.server.utils.Utils;
import org.allaymc.server.world.dimension.DimensionId;
import org.allaymc.server.world.light.NoLightEngine;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class AllayWorldPool implements WorldPool {

    private final Map<String, AllayWorld> worlds;
    @Getter
    private final Path worldFolder;
    private final WorldSettings worldConfig;

    public AllayWorldPool() {
        this.worlds = new ConcurrentHashMap<>();
        this.worldFolder = Path.of("worlds");
        this.worldConfig = Objects.requireNonNull(ConfigManager.create(
                WorldSettings.class,
                Utils.createConfigInitializer(this.worldFolder.resolve("world-settings.yml"))
        ));
    }

    public void loadWorlds() {
        this.worldConfig.worlds().forEach((name, setting) -> {
            try {
                loadWorld(name, setting);
            } catch (Exception e) {
                log.error("Error when loading world {}, skipped.", name, e);
            }
        });
    }

    public void shutdown() {
        this.worlds.values().forEach(AllayWorld::shutdown);

        // Waiting for all worlds to fully shutdown
        while (this.worlds.values().stream().anyMatch(world -> world.getState() != WorldState.STOPPED)) {
            Thread.onSpinWait();
        }

        // Save world settings
        this.worldConfig.worlds().clear();
        this.worlds.forEach((name, world) -> {
            if (!world.isRuntimeOnly()) {
                this.worldConfig.worlds().put(name, toWorldSetting(world));
            }
        });
        this.worldConfig.save();
    }

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

        this.worlds.put(world.getName(), world);
        world.startTick();
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_LOADED, name));
    }

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

    @Override
    public World getWorld(String name) {
        return this.worlds.get(name);
    }

    @Override
    public Map<String, World> getWorlds() {
        return Collections.unmodifiableMap(this.worlds);
    }

    @Override
    public World getDefaultWorld() {
        return getWorld(worldConfig.defaultWorld());
    }

    private void loadWorld(String name, WorldSettings.WorldSetting setting) {
        var storage = Registries.WORLD_STORAGE_FACTORIES.get(setting.storageType()).apply(this.worldFolder.resolve(name));
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

    private WorldGenerator tryCreateWorldGenerator(WorldSettings.WorldSetting.DimensionSetting settings) {
        var factory = Registries.WORLD_GENERATOR_FACTORIES.get(settings.generatorType());
        if (factory == null) {
            log.error("Cannot find world generator {}, fallback to the VOID generator", settings.generatorType());
            factory = Registries.WORLD_GENERATOR_FACTORIES.get("VOID");
        }

        try {
            return factory.apply(settings.generatorPreset());
        } catch (Throwable t) {
            log.error("Error while creating {} type world generator with the preset {}, fallback to the VOID generator", settings.generatorType(), settings.generatorPreset(), t);
            return Registries.WORLD_GENERATOR_FACTORIES.get("VOID").apply(null);
        }
    }

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

    private WorldSettings.WorldSetting.DimensionSetting toWorldDimensionSetting(Dimension dimension) {
        var generator = dimension.getWorldGenerator();
        var lightEnabled = !(((AllayDimension) dimension).getLightEngine() instanceof NoLightEngine);
        return new WorldSettings.WorldSetting.DimensionSetting(generator.getName(), generator.getPreset(), lightEnabled);
    }

    private static void validateDimensionType(DimensionType dimensionType) {
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

    private static Identifier normalizeDimensionIdentifier(String rawIdentifier) {
        return new Identifier(rawIdentifier);
    }

    @Getter
    @Accessors(fluent = true)
    public static class WorldSettings extends OkaeriConfig {

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

        @Comment("The default world is the world that newly joined players will be in")
        @CustomKey("default-world")
        private String defaultWorld = "world";

        @Getter
        @Accessors(fluent = true)
        public static class WorldSetting extends OkaeriConfig {
            @CustomKey("storage-type")
            private String storageType;

            @Comment("If set to true, virtual thread will be used as the ticking thread of the world and the dimensions in the world")
            @CustomKey("use-virtual-thread")
            private boolean useVirtualThread;

            private Map<String, DimensionSetting> dimensions;

            private DimensionSetting overworld;

            // Can be null
            private DimensionSetting nether;

            // Can be null
            @CustomKey("the-end")
            private DimensionSetting theEnd;

            public WorldSetting(String storageType, boolean useVirtualThread, Map<String, DimensionSetting> dimensions) {
                this(storageType, useVirtualThread, dimensions, null, null, null);
            }

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

            public Map<Identifier, DimensionSetting> normalizedDimensions() {
                var normalized = new LinkedHashMap<Identifier, DimensionSetting>();
                if (dimensions != null && !dimensions.isEmpty()) {
                    dimensions.forEach((identifier, setting) -> normalized.put(normalizeDimensionIdentifier(identifier), setting));
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

            @Getter
            @Accessors(fluent = true)
            @AllArgsConstructor
            public static class DimensionSetting extends OkaeriConfig {
                @CustomKey("generator-type")
                private String generatorType;

                @CustomKey("generator-preset")
                private String generatorPreset;

                @Comment("If set to false, light calculation will be disabled for this dimension")
                @Comment("All light queries will return max value (15)")
                @CustomKey("enable-light-calculation")
                private boolean enableLightCalculation;

                public DimensionSetting(String generatorType, String generatorPreset) {
                    this(generatorType, generatorPreset, true);
                }
            }
        }
    }
}
