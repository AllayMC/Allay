package org.allaymc.server.block.component.wood;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockWoodBaseComponent;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "pillar_axis")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "stripped_bit")
public class BlockNewWoodBaseComponentImpl extends BlockBaseComponentImpl implements BlockWoodBaseComponent {
    public BlockNewWoodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public BlockState getStrippedBlockState(BlockState unstrippedBlockState) {
        return unstrippedBlockState.setProperty(VanillaBlockPropertyTypes.STRIPPED_BIT, true);
    }

    @Override
    public boolean isStripped(BlockState blockState) {
        return blockState.getPropertyValue(VanillaBlockPropertyTypes.STRIPPED_BIT);
    }
}
