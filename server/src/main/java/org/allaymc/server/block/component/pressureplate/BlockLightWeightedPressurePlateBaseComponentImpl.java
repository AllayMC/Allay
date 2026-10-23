package org.allaymc.server.block.component.pressureplate;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockLightWeightedPressurePlateBaseComponentImpl} specializes pressure plate
 * signal strength for the light weighted variant. The inherited component manages
 * support and scheduled entity checks, while this calculation assigns signal units to
 * each intersecting entity and caps the output at maximum redstone power.
 *
 * @author daoge_cmd
 */
public class BlockLightWeightedPressurePlateBaseComponentImpl extends BlockPressurePlateBaseComponentImpl {

    /**
     * Creates the light weighted plate component for the supplied block type.
     */
    public BlockLightWeightedPressurePlateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one signal unit per entity intersecting the plate's detection bounds. The
     * count is capped at {@code MAX_REDSTONE_POWER}, so additional entities cannot
     * exceed the redstone signal property's range.
     */
    @Override
    protected int calculateSignal(Block block) {
        int entityCount = countEntitiesOnPlate(block);
        // Clamp crowded plates to the signal property's supported redstone range.
        return Math.min(entityCount, MAX_REDSTONE_POWER);
    }
}
