package org.allaymc.server.registry.populator;

import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.dimension.AllayDimensionType;
import org.allaymc.server.world.dimension.DimensionId;

/**
 * @author daoge_cmd
 */
public class DimensionTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        DimensionTypes.OVERWORLD = new AllayDimensionType(
                DimensionId.OVERWORLD.ordinal(),
                DimensionId.OVERWORLD.getIdentifier(),
                -64,
                319,
                true,
                true,
                false,
                false
        );
        DimensionTypes.NETHER = new AllayDimensionType(
                DimensionId.NETHER.ordinal(),
                DimensionId.NETHER.getIdentifier(),
                0,
                127,
                false,
                false,
                true,
                true
        );
        DimensionTypes.THE_END = new AllayDimensionType(
                DimensionId.THE_END.ordinal(),
                DimensionId.THE_END.getIdentifier(),
                0,
                255,
                false,
                false,
                false,
                false
        );
    }
}
