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
 * WorldPool contains all worlds in the server.
 *
 * @author daoge_cmd
 */
public interface WorldPool {
    /**
     * Retrieves the path to the folder where worlds are stored.
     *
     * @return the path to the world folder
     */
    Path getWorldFolder();

    /**
     * Get the world by the name.
     *
     * @param name the name of the world
     * @return the world, or {@code null} if the world is not found
     */
    World getWorld(String name);

    /**
     * Get all worlds in the server.
     *
     * @return all worlds in the server
     */
    @UnmodifiableView
    Map<String, World> getWorlds();

    /**
     * Get the default world.
     *
     * @return the default world
     */
    World getDefaultWorld();

    /**
     * Create a world with the provided name, storage and generators for different dimensions.
     *
     * @param name               the name of the world to be loaded
     * @param storage            the storage used for reading and writing chunks and world data
     * @param overworldGenerator the generator used to initialize or generate the overworld dimension
     * @param netherGenerator    the generator used to initialize or generate the nether dimension, or
     *                           {@code null} to disable nether dimension
     * @param theEndGenerator    the generator used to initialize or generate the end dimension, or
     *                           {@code null} to disable the end dimension
     * @throws IllegalArgumentException if the world with the specific name already exists
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
     * Create a world with the provided name, storage, and dimension settings.
     *
     * @param worldSetting       the setting of the world
     * @param dimensionSettings  the settings of the dimensions keyed by their identifiers. Each key must match
     *                           {@link DimensionSetting#dimensionType()}{@code .getIdentifier()}.
     * @throws IllegalArgumentException if the world with the specific name already exists
     */
    void loadWorld(WorldSetting worldSetting, Map<Identifier, DimensionSetting> dimensionSettings);

    /**
     * Unload the world with the specific name.
     *
     * @param name the name of the world
     * @throws IllegalArgumentException if the world with the specific name is not exists, or the world is the default world
     */
    void unloadWorld(String name);

    /**
     * Get the global spawn point of the server.
     * <p>
     * Global spawn point is the location of the player who joins the server for the first time.
     *
     * @return the global spawn point of the server
     */
    default Location3ic getGlobalSpawnPoint() {
        var vec = getDefaultWorld().getWorldData().getSpawnPoint();
        return new Location3i(vec.x(), vec.y(), vec.z(), getDefaultWorld().getOverWorld());
    }

    /**
     * WorldSetting contains the setting of a world when creating a new world.
     *
     * @param name             the name of the world
     * @param storage          the storage used for this world
     * @param useVirtualThread whether to use the virtual thread as the ticking thread of the world and the dimensions in the world
     */
    @Builder
    record WorldSetting(String name, WorldStorage storage, boolean useVirtualThread) {
    }

    /**
     * DimensionSetting contains the setting of a dimension when creating a new world.
     *
     * @param dimensionType          the dimension type
     * @param worldGenerator         the world generator used for this dimension
     * @param enableLightCalculation whether light calculation is enabled in this dimension
     */
    @Builder
    record DimensionSetting(DimensionType dimensionType, WorldGenerator worldGenerator, boolean enableLightCalculation) {
    }
}
