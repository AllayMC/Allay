package org.allaymc.server.registry.populator;

import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.dimension.AllayDimensionType;
import org.allaymc.server.world.dimension.BuiltinDimensionSettings;
import org.allaymc.server.world.dimension.DimensionId;

/**
 * @author daoge_cmd
 */
public class DimensionTypeRegistryPopulator implements Runnable {
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
