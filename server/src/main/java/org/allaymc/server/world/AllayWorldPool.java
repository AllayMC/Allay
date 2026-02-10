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
import org.allaymc.api.eventbus.event.world.WorldLoadEvent;
import org.allaymc.api.eventbus.event.world.WorldUnloadEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.utils.Utils;
import org.allaymc.server.world.light.NoLightEngine;

import java.nio.file.Path;
import java.util.Collections;
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
    public void loadWorld(WorldSetting worldSetting, DimensionSetting overworldSetting, DimensionSetting netherSetting, DimensionSetting theEndSetting) {
        var name = worldSetting.name();
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_LOADING, name));
        if (worlds.containsKey(name)) {
            throw new IllegalArgumentException("World " + name + " is already loaded");
        }

        var useVirtualThread = worldSetting.useVirtualThread();
        AllayWorld world;
        try {
            world = new AllayWorld(name, worldSetting.storage(), useVirtualThread);
        } catch (Throwable t) {
            log.error("Error while initializing world {}", name, t);
            return;
        }

        // Load overworld dimension
        world.addDimension(new AllayDimension(world, overworldSetting.worldGenerator(), DimensionInfo.OVERWORLD, overworldSetting.enableLightCalculation(), useVirtualThread));

        // Load nether and the end dimension if they are not null
        if (netherSetting != null) {
            world.addDimension(new AllayDimension(world, netherSetting.worldGenerator(), DimensionInfo.NETHER, netherSetting.enableLightCalculation(), useVirtualThread));
        }
        if (theEndSetting != null) {
            world.addDimension(new AllayDimension(world, theEndSetting.worldGenerator(), DimensionInfo.THE_END, theEndSetting.enableLightCalculation(), useVirtualThread));
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

        var overworldGenerator = tryCreateWorldGenerator(Preconditions.checkNotNull(setting.overworld(), "World must has overworld dimension"));
        var netherGenerator = setting.nether() == null ? null : tryCreateWorldGenerator(setting.nether());
        var theEndGenerator = setting.theEnd() == null ? null : tryCreateWorldGenerator(setting.theEnd());

        loadWorld(
                new WorldSetting(name, storage, false),
                new DimensionSetting(overworldGenerator, setting.overworld().enableLightCalculation()),
                netherGenerator != null ? new DimensionSetting(netherGenerator, setting.nether().enableLightCalculation()) : null,
                theEndGenerator != null ? new DimensionSetting(theEndGenerator, setting.theEnd().enableLightCalculation()) : null
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

    private WorldSettings.WorldSetting toWorldSetting(World world) {
        var owGenerator = world.getOverWorld().getWorldGenerator();

        WorldGenerator netherGenerator = null;
        if (world.getNether() != null) {
            netherGenerator = world.getNether().getWorldGenerator();
        }

        WorldGenerator theEndGenerator = null;
        if (world.getTheEnd() != null) {
            theEndGenerator = world.getTheEnd().getWorldGenerator();
        }

        var overworldLightEnabled = !(((AllayDimension) world.getOverWorld()).getLightEngine() instanceof NoLightEngine);
        var netherLightEnabled = world.getNether() == null || !(((AllayDimension) world.getNether()).getLightEngine() instanceof NoLightEngine);
        var theEndLightEnabled = world.getTheEnd() == null || !(((AllayDimension) world.getTheEnd()).getLightEngine() instanceof NoLightEngine);

        return new WorldSettings.WorldSetting(
                world.getWorldStorage().getName(),
                world.isVirtualTickingThread(),
                new WorldSettings.WorldSetting.DimensionSetting(owGenerator.getName(), owGenerator.getPreset(), overworldLightEnabled),
                netherGenerator != null ? new WorldSettings.WorldSetting.DimensionSetting(netherGenerator.getName(), netherGenerator.getPreset(), netherLightEnabled) : null,
                theEndGenerator != null ? new WorldSettings.WorldSetting.DimensionSetting(theEndGenerator.getName(), theEndGenerator.getPreset(), theEndLightEnabled) : null
        );
    }

    @Getter
    @Accessors(fluent = true)
    public static class WorldSettings extends OkaeriConfig {

        @Setter
        @CustomKey("worlds")
        private Map<String, WorldSetting> worlds = Map.of("world", new WorldSetting(
                "LEVELDB", false,
                new WorldSetting.DimensionSetting(
                        "FLAT",
                        ""
                ), null, null
        ));

        @Comment("The default world is the world that newly joined players will be in")
        @CustomKey("default-world")
        private String defaultWorld = "world";

        @Getter
        @Accessors(fluent = true)
        @AllArgsConstructor
        public static class WorldSetting extends OkaeriConfig {
            @CustomKey("storage-type")
            private String storageType;

            @Comment("If set to true, virtual thread will be used as the ticking thread of the world and the dimensions in the world")
            @CustomKey("use-virtual-thread")
            private boolean useVirtualThread;

            private DimensionSetting overworld;

            // Can be null
            private DimensionSetting nether;

            // Can be null
            @CustomKey("the-end")
            private DimensionSetting theEnd;

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
