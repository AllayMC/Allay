package org.allaymc.server.block.component.wood;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "pillar_axis")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "stripped_bit")
public class BlockOldWoodBaseComponentImpl extends BlockWoodBaseComponentImpl {
    public BlockOldWoodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, VanillaBlockId strippedType) {
        super(blockType, strippedType);
    }

    @Override
    public BlockState getStrippedBlockState(BlockState unstrippedBlockState) {
        return strippedType.getBlockType().ofState(unstrippedBlockState.getPropertyValues().get(VanillaBlockPropertyTypes.PILLAR_AXIS));
    }
}
