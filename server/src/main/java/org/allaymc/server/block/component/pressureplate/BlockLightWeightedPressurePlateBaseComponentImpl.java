package org.allaymc.server.block.component.pressureplate;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;

/**
 * Implementation of light weighted pressure plate (gold).
 * Signal strength equals the number of entities (max 15).
 *
 * @author daoge_cmd
 */
public class BlockLightWeightedPressurePlateBaseComponentImpl extends BlockPressurePlateBaseComponentImpl {

    public BlockLightWeightedPressurePlateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    protected int calculateSignal(Block block) {
        int entityCount = countEntitiesOnPlate(block);
        // Light weighted: 1 entity = 1 signal, max 15
        return Math.min(entityCount, MAX_REDSTONE_POWER);
    }
}
