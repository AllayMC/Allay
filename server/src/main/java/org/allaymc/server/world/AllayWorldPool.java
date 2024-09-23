package org.allaymc.server.world;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.world.WorldLoadEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.WorldSettings;
import org.allaymc.api.world.generator.WorldGenerator;
import org.cloudburstmc.protocol.common.util.Preconditions;

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
    public static final Path WORLDS_FOLDER = Path.of("worlds");
    public static final String SETTINGS_FILE_NAME = "world-settings.yml";

    private final Map<String, AllayWorld> worlds = new ConcurrentHashMap<>();
    @Getter
    private WorldSettings worldConfig;

    public AllayWorldPool() {
        loadWorldConfig();
    }

    public void loadWorlds() {
        worldConfig.worlds().forEach(this::loadWorld);
    }

    public void shutdown() {
        worlds.values().forEach(AllayWorld::shutdown);
    }

    @Override
    public void loadWorld(String name, WorldSettings.WorldEntry settings) {
        if (!settings.enable()) return;

        log.info(I18n.get().tr(TrKeys.A_WORLD_LOADING, name));
        if (worlds.containsKey(name))
            throw new IllegalArgumentException("World " + name + " already exists");

        var overworldSettings = settings.overworld();
        Preconditions.checkNotNull(overworldSettings, "World must has overworld dimension");

        var netherSettings = settings.nether();
        var theEndSettings = settings.theEnd();
        var storage = Registries.WORLD_STORAGE_FACTORIES.get(settings.storageType()).apply(WORLDS_FOLDER.resolve(name));
        var world = new AllayWorld(storage);
        // Load overworld dimension
        var overworld = new AllayDimension(world, tryCreateWorldGenerator(overworldSettings), DimensionInfo.OVERWORLD);
        world.setDimension(overworld);
        // Load nether and the end dimension if they are not null
        if (netherSettings != null) {
            var nether = new AllayDimension(world, tryCreateWorldGenerator(netherSettings), DimensionInfo.NETHER);
            world.setDimension(nether);
        }

        if (theEndSettings != null) {
            var theEnd = new AllayDimension(world, tryCreateWorldGenerator(theEndSettings), DimensionInfo.THE_END);
            world.setDimension(theEnd);
        }

        if (addWorld(world)) {
            log.info(I18n.get().tr(TrKeys.A_WORLD_LOADED, name));
            if (!worldConfig.worlds().containsKey(name)) {
                worldConfig.worlds().put(name, settings);
                worldConfig.save();
            }
        }
    }

    private WorldGenerator tryCreateWorldGenerator(WorldSettings.WorldEntry.DimensionSettings settings) {
        var factory = Registries.WORLD_GENERATOR_FACTORIES.get(settings.generatorType());
        if (factory == null) {
            log.error("Cannot find world generator {}", settings.generatorType());
            factory = Registries.WORLD_GENERATOR_FACTORIES.get("VOID");
        }
        return factory.apply(settings.generatorPreset());
    }

    private void loadWorldConfig() {
        worldConfig = Objects.requireNonNull(ConfigManager.create(WorldSettings.class, Utils.createConfigInitializer(WORLDS_FOLDER.resolve(SETTINGS_FILE_NAME))));

        int changeNumber = 0;
        for (var file : Objects.requireNonNull(WORLDS_FOLDER.toFile().listFiles(File::isDirectory))) {
            if (!worldConfig.worlds().containsKey(file.getName())) {
                var worldEntry = WorldSettings.WorldEntry.builder()
                        .enable(true)
                        .overworld(new WorldSettings.WorldEntry.DimensionSettings("VOID", ""))
                        .nether(null)
                        .theEnd(null)
                        .storageType("LEVELDB")
                        .build();
                worldConfig.worlds().put(file.getName(), worldEntry);
                changeNumber++;
            }
        }

        if (changeNumber != 0) worldConfig.save();
    }

    @Override
    public World getWorld(String name) {
        return worlds.get(name);
    }

    @Override
    public Map<String, World> getWorlds() {
        return Collections.unmodifiableMap(worlds);
    }

    private boolean addWorld(AllayWorld world) {
        var event = new WorldLoadEvent(world);
        event.call();
        if (event.isCancelled()) return false;

        worlds.put(world.getWorldData().getName(), world);
        world.startTick();
        return true;
    }

    @Override
    public World getDefaultWorld() {
        return getWorld(worldConfig.defaultWorld());
    }
}