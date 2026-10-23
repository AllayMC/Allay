package org.allaymc.api.world;

import lombok.Builder;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.storage.WorldStorage;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@code WorldPool} exposes loaded worlds and the operations that admit or
 * remove them. Worlds are keyed by their stable names, and a designated
 * default world supplies the initial global spawn. Load and unload events can
 * cancel pool membership changes before their corresponding start or shutdown
 * request.
 *
 * @author daoge_cmd
 */
public interface WorldPool {
    /**
     * Returns the base folder used by configured world storage backends.
     */
    Path getWorldFolder();

    /**
     * Returns the loaded world registered under the supplied stable name. A
     * {@code null} result means the pool currently has no such entry; this
     * lookup does not load storage.
     */
    World getWorld(String name);

    /**
     * Returns an unmodifiable view of the loaded-world mapping keyed by name.
     * The view retains existing world references and reflects later pool
     * membership changes instead of copying world instances.
     */
    @UnmodifiableView
    Map<String, World> getWorlds();

    /**
     * Returns the designated default world, resolving configured membership
     * when needed. A missing configured entry can produce {@code null}, so
     * startup must establish a loaded default before callers use its spawn.
     */
    World getDefaultWorld();

    /**
     * Loads a world with the overworld and optional built-in dimensions.
     * {@code null} nether or end generators omit those dimensions; the
     * convenience settings enable lighting and select platform threads before
     * delegating to the full loader.
     */
    default void loadWorld(
            String name, WorldStorage storage,
            WorldGenerator overworldGenerator,
            WorldGenerator netherGenerator,
            WorldGenerator theEndGenerator
    ) {
        var dimensionSettings = new LinkedHashMap<Identifier, DimensionSetting>();
        dimensionSettings.put(DimensionTypes.OVERWORLD.getIdentifier(), new DimensionSetting(DimensionTypes.OVERWORLD, overworldGenerator, true));
        if (netherGenerator != null) {
            dimensionSettings.put(DimensionTypes.NETHER.getIdentifier(), new DimensionSetting(DimensionTypes.NETHER, netherGenerator, true));
        }
        if (theEndGenerator != null) {
            dimensionSettings.put(DimensionTypes.THE_END.getIdentifier(), new DimensionSetting(DimensionTypes.THE_END, theEndGenerator, true));
        }
        loadWorld(
                new WorldSetting(name, storage, false),
                dimensionSettings
        );
    }

    /**
     * Prepares and admits a world using identifier-keyed dimension settings.
     * Each key must match its dimension type identifier, the overworld is
     * required, and accepted load-event dispatch precedes registration and
     * tick startup. An existing world name or invalid dimension metadata is
     * rejected.
     */
    void loadWorld(WorldSetting worldSetting, Map<Identifier, DimensionSetting> dimensionSettings);

    /**
     * Requests shutdown and removes a named world after accepted unload-event
     * dispatch. The default world and missing names are rejected with {@code
     * IllegalArgumentException}; removal returns without waiting for the world
     * to finish cleanup.
     */
    void unloadWorld(String name);

    /**
     * Returns a new initial-player spawn location from the default world. The
     * configured default must already be loaded; the location uses its
     * metadata coordinates and overworld dimension.
     */
    default Location3ic getGlobalSpawnPoint() {
        var vec = getDefaultWorld().getWorldData().getSpawnPoint();
        return new Location3i(vec.x(), vec.y(), vec.z(), getDefaultWorld().getOverWorld());
    }

    /**
     * {@code WorldSetting} groups the name, storage, and thread selection used
     * to create a world.
     *
     * @param name stable world pool key
     * @param storage retained backend for world persistence
     * @param useVirtualThread virtual thread selection for simulation loops
     */
    @Builder
    record WorldSetting(String name, WorldStorage storage, boolean useVirtualThread) {
    }

    /**
     * {@code DimensionSetting} groups a dimension type, generator, and
     * lighting choice for world loading.
     *
     * @param dimensionType registered dimension identity and bounds
     * @param worldGenerator retained generator attached during loading
     * @param enableLightCalculation whether the dimension computes stored
     *     light
     */
    @Builder
    record DimensionSetting(DimensionType dimensionType, WorldGenerator worldGenerator, boolean enableLightCalculation) {
    }
}
