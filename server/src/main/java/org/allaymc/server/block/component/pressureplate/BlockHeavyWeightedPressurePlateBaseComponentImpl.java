package org.allaymc.server.block.component.pressureplate;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;

/**
 * Implementation of heavy weighted pressure plate (iron).
 * Signal strength equals ceil(entities / 10), max 15.
 *
 * @author daoge_cmd
 */
public class BlockHeavyWeightedPressurePlateBaseComponentImpl extends BlockPressurePlateBaseComponentImpl {

    public BlockHeavyWeightedPressurePlateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    protected int calculateSignal(Block block) {
        int entityCount = countEntitiesOnPlate(block);
        if (entityCount == 0) {
            return 0;
        }
        // Heavy weighted: 10 entities = 1 signal, ceiling division, max 15
        int signal = (entityCount + 9) / 10;
        return Math.min(signal, MAX_REDSTONE_POWER);
    }
}
