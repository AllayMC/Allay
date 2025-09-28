package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import eu.okaeri.configs.ConfigManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.world.WorldLoadEvent;
import org.allaymc.api.eventbus.event.world.WorldUnloadEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.WorldSetting;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;

import java.io.File;
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
    private static final Path WORLDS_FOLDER = Path.of("worlds");

    private final Map<String, AllayWorld> worlds;
    @Getter
    private final WorldSettings worldConfig;

    public AllayWorldPool() {
        this.worlds = new ConcurrentHashMap<>();
        this.worldConfig = Objects.requireNonNull(ConfigManager.create(
                WorldSettings.class,
                Utils.createConfigInitializer(WORLDS_FOLDER.resolve("world-settings.yml"))
        ));

        int changeNumber = 0;
        for (var file : Objects.requireNonNull(WORLDS_FOLDER.toFile().listFiles(File::isDirectory))) {
            if (!this.worldConfig.worlds().containsKey(file.getName())) {
                var worldEntry = WorldSetting.builder()
                        .enable(true)
                        .overworld(new WorldSetting.DimensionSettings("VOID", ""))
                        .nether(null)
                        .theEnd(null)
                        .storageType("LEVELDB")
                        .build();
                this.worldConfig.worlds().put(file.getName(), worldEntry);
                changeNumber++;
            }
        }

        if (changeNumber != 0) {
            this.worldConfig.save();
        }
    }

    public void shutdown() {
        this.worlds.values().forEach(AllayWorld::shutdown);
        // Waiting for all worlds to fully shutdown
        while (worlds.values().stream().anyMatch(world -> world.getState() != WorldState.STOPPED)) {
            Thread.onSpinWait();
        }
        this.worldConfig.save();
    }

    public void loadWorlds() {
        this.worldConfig.worlds().forEach((name, setting) -> {
            try {
                this.loadWorld(name, setting);
            } catch (Exception e) {
                log.error("Error when loading world {}, skipped.", name, e);
            }
        });
    }

    @Override
    public void loadWorld(String name, WorldSetting setting) {
        if (!setting.enable()) {
            return;
        }

        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_LOADING, name));
        if (worlds.containsKey(name)) {
            throw new IllegalArgumentException("World " + name + " is already loaded");
        }

        var overworldSettings = setting.overworld();
        Preconditions.checkNotNull(overworldSettings, "World must has overworld dimension");

        var netherSettings = setting.nether();
        var theEndSettings = setting.theEnd();
        var storage = Registries.WORLD_STORAGE_FACTORIES.get(setting.storageType()).apply(WORLDS_FOLDER.resolve(name));
        if (storage == null) {
            log.error("Cannot find world storage type {}", setting.storageType());
            storage = Registries.WORLD_STORAGE_FACTORIES.get("LEVELDB").apply(WORLDS_FOLDER.resolve(name));
        }
        AllayWorld world;
        try {
            world = new AllayWorld(name, storage);
        } catch (Throwable t) {
            log.error("Error while initializing world {}", name, t);
            return;
        }
        // Load overworld dimension
        world.addDimension(new AllayDimension(world, tryCreateWorldGenerator(overworldSettings), DimensionInfo.OVERWORLD));

        // Load nether and the end dimension if they are not null
        if (netherSettings != null) {
            world.addDimension(new AllayDimension(world, tryCreateWorldGenerator(netherSettings), DimensionInfo.NETHER));
        }
        if (theEndSettings != null) {
            world.addDimension(new AllayDimension(world, tryCreateWorldGenerator(theEndSettings), DimensionInfo.THE_END));
        }

        if (addWorld(world)) {
            log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_LOADED, name));
            if (setting.runtimeOnly() || this.worldConfig.worlds().containsKey(name)) {
                // Runtime-only world won't be saved to world-settings.yml
                return;
            }
            this.worldConfig.worlds().put(name, setting);
            this.worldConfig.save();
        }
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

    private boolean addWorld(AllayWorld world) {
        var event = new WorldLoadEvent(world);
        if (!event.call()) {
            return false;
        }

        this.worlds.put(world.getName(), world);
        world.startTick();
        return true;
    }

    private WorldGenerator tryCreateWorldGenerator(WorldSetting.DimensionSettings settings) {
        var factory = Registries.WORLD_GENERATOR_FACTORIES.get(settings.generatorType());
        if (factory == null) {
            log.error("Cannot find world generator {}", settings.generatorType());
            factory = Registries.WORLD_GENERATOR_FACTORIES.get("VOID");
        }
        try {
            return factory.apply(settings.generatorPreset());
        } catch (Throwable t) {
            log.error("Error while creating {} type world generator with the preset {}", settings.generatorType(), settings.generatorPreset(), t);
            return Registries.WORLD_GENERATOR_FACTORIES.get("VOID").apply(null);
        }
    }
}
