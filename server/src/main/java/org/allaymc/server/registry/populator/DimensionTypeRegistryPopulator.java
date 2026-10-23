package org.allaymc.server.registry.populator;

import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.dimension.AllayDimensionType;
import org.allaymc.server.world.dimension.BuiltinDimensionSettings;
import org.allaymc.server.world.dimension.DimensionId;

/**
 * {@code DimensionTypeRegistryPopulator} supplies built-in dimension definitions for the
 * overworld, Nether, and End. Loaded settings determine inclusive height bounds, while each
 * definition fixes its skylight, sleeping, respawn-anchor, and evaporation behavior; constructors
 * register the dimensions before the public constants receive their references.
 *
 * @author daoge_cmd
 */
public class DimensionTypeRegistryPopulator implements Runnable {
    /**
     * Constructs and assigns the three built-in dimension types using loaded height settings.
     * Dimension settings and the registry must already be initialized, and construction failures
     * leave any previously registered dimensions and assigned constants intact.
     */
    @Override
    public void run() {
        var builtinDimensionSettings = BuiltinDimensionSettings.get();
        DimensionTypes.OVERWORLD = new AllayDimensionType(
                DimensionId.OVERWORLD.getId(),
                DimensionId.OVERWORLD.getIdentifier(),
                builtinDimensionSettings.getMinHeight(DimensionId.OVERWORLD),
                builtinDimensionSettings.getMaxHeight(DimensionId.OVERWORLD),
                true,
                true,
                false,
                false
        );
        DimensionTypes.NETHER = new AllayDimensionType(
                DimensionId.NETHER.getId(),
                DimensionId.NETHER.getIdentifier(),
                builtinDimensionSettings.getMinHeight(DimensionId.NETHER),
                builtinDimensionSettings.getMaxHeight(DimensionId.NETHER),
                false,
                false,
                true,
                true
        );
        DimensionTypes.THE_END = new AllayDimensionType(
                DimensionId.THE_END.getId(),
                DimensionId.THE_END.getIdentifier(),
                builtinDimensionSettings.getMinHeight(DimensionId.THE_END),
                builtinDimensionSettings.getMaxHeight(DimensionId.THE_END),
                false,
                false,
                false,
                false
        );
    }
}
