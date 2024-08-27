package org.allaymc.server.block.component.wood;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockWoodBaseComponent;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "pillar_axis")
public class BlockWoodBaseComponentImpl extends BlockBaseComponentImpl implements BlockWoodBaseComponent {
    protected final BlockId strippedType;

    public BlockWoodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId strippedType) {
        super(blockType);
        this.strippedType = strippedType;
    }

    @Override
    public BlockState getStrippedBlockState(BlockState unstrippedBlockState) {
        return strippedType.getBlockType().copyPropertyValuesFrom(unstrippedBlockState);
    }

    @Override
    public boolean isStripped(BlockState blockState) {
        return blockState.getBlockType().equals(strippedType.getBlockType());
    }
}
