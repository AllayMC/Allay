package org.allaymc.server.world;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldConfig;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.generator.WorldGeneratorFactory;
import org.allaymc.api.world.storage.WorldStorageFactory;
import org.cloudburstmc.protocol.common.util.Preconditions;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayWorldPool implements WorldPool {

    public static final Path WORLDS_FOLDER = Path.of("worlds");
    public static final String SETTINGS_FILE_NAME = "worlds.yml";
    @Getter
    private WorldConfig worldConfig;
    private final Map<String, World> worlds = new ConcurrentHashMap<>();

    public AllayWorldPool() {
        loadWorldConfig();
    }

    @Override
    public void loadWorlds() {
        worldConfig.worlds().forEach(this::loadWorld);
    }

    @Override
    public void close() {
        worlds.values().forEach(World::close);
    }

    @Override
    public void loadWorld(String name, WorldConfig.WorldSettings settings) {
        log.info(I18n.get().tr(TrKeys.A_WORLD_LOADING, name));
        if (worlds.containsKey(name))
            throw new IllegalArgumentException("World " + name + " already exists");
        var overworldSettings = settings.overworld();
        Preconditions.checkNotNull(overworldSettings, "A world must has overworld dimension");
        var netherSettings = settings.nether();
        var theEndSettings = settings.theEnd();
        var storage = WorldStorageFactory.getFactory().createWorldStorage(settings.storageType(), WORLDS_FOLDER.resolve(name));
        var world = new AllayWorld(storage);
        // Load overworld dimension
        var overworld = new AllayDimension(world, WorldGeneratorFactory.getFactory().createWorldGenerator(overworldSettings.generatorType(), overworldSettings.generatorPreset()), DimensionInfo.OVERWORLD);
        world.setDimension(overworld);
        // Load nether and the end dimension if they are not null
        if (netherSettings != null) {
            var nether = new AllayDimension(world, WorldGeneratorFactory.getFactory().createWorldGenerator(netherSettings.generatorType(), netherSettings.generatorPreset()), DimensionInfo.NETHER);
            world.setDimension(nether);
        }
        if (theEndSettings != null) {
            var theEnd = new AllayDimension(world, WorldGeneratorFactory.getFactory().createWorldGenerator(theEndSettings.generatorType(), theEndSettings.generatorPreset()), DimensionInfo.THE_END);
            world.setDimension(theEnd);
        }
        addWorld(world);
        log.info(I18n.get().tr(TrKeys.A_WORLD_LOADED, name));
        if (!worldConfig.worlds().containsKey(name)) {
            worldConfig.worlds().put(name, settings);
            worldConfig.save();
        }
    }

    private void loadWorldConfig() {
        worldConfig = ConfigManager.create(WorldConfig.class, it -> {
            it.withConfigurer(new YamlSnakeYamlConfigurer()); // specify configurer implementation, optionally additional serdes packages
            it.withBindFile(WORLDS_FOLDER.resolve(SETTINGS_FILE_NAME)); // specify Path, File or pathname
            it.withRemoveOrphans(true); // automatic removal of undeclared keys
            it.saveDefaults(); // save file if does not exists
            it.load(true); // load and save to update comments/new fields
        });
    }

    @Override
    public World getWorld(String name) {
        return worlds.get(name);
    }

    @Override
    public Map<String, World> getWorlds() {
        return Collections.unmodifiableMap(worlds);
    }

    private void addWorld(World world) {
        worlds.put(world.getWorldData().getName(), world);
        world.startTick();
    }

    @Override
    public World getDefaultWorld() {
        return getWorld(worldConfig.defaultWorld());
    }
}
