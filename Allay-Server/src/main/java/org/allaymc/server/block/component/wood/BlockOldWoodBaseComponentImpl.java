package org.allaymc.server.block.component.wood;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
public class BlockOldWoodBaseComponentImpl extends BlockWoodBaseComponentImpl {
    public BlockOldWoodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId strippedType) {
        super(blockType, strippedType);
    }

    @Override
    public BlockState getStrippedBlockState(BlockState unstrippedBlockState) {
        return strippedType.getBlockType().ofState(unstrippedBlockState.getPropertyValues().get(BlockPropertyTypes.PILLAR_AXIS));
    }
}
