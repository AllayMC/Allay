package org.allaymc.server.block.component.pressureplate;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockHeavyWeightedPressurePlateBaseComponentImpl} specializes pressure plate
 * signal strength for the heavy weighted variant. The inherited component manages
 * support and scheduled entity checks, while this calculation assigns signal units to a
 * ceiling-rounded group of ten entities and caps the output at maximum redstone power.
 *
 * @author daoge_cmd
 */
public class BlockHeavyWeightedPressurePlateBaseComponentImpl extends BlockPressurePlateBaseComponentImpl {

    /**
     * Creates the heavy weighted plate component for the supplied block type.
     */
    public BlockHeavyWeightedPressurePlateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one signal unit for each started group of ten currently intersecting
     * entities. Empty plates return {@code 0}, and the ceiling-rounded result is capped
     * at {@code MAX_REDSTONE_POWER}.
     */
    @Override
    protected int calculateSignal(Block block) {
        int entityCount = countEntitiesOnPlate(block);
        if (entityCount == 0) {
            return 0;
        }
        // Rounding upward lets even an incomplete ten-entity group activate the plate.
        int signal = (entityCount + 9) / 10;
        return Math.min(signal, MAX_REDSTONE_POWER);
    }
}
