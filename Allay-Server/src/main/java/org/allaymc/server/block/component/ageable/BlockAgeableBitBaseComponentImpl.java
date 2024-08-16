package org.allaymc.server.block.component.ageable;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.AGE_BIT;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemBoneMealStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "age_bit")
public class BlockAgeableBitBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockAgeableBitBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockState) {
        //TODO: add random
        if (!blockState.blockState().getPropertyValue(AGE_BIT)) {
            blockState.blockState().setProperty(AGE_BIT, true);
        }

        grow();
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;
        if (itemStack instanceof ItemBoneMealStack) {
            grow();
        }

        return true;
    }

    public void grow() {

    }
}
