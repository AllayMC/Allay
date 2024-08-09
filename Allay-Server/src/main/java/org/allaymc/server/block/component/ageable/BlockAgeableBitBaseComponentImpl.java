package org.allaymc.server.block.component.ageable;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.AGE_BIT;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemBoneMealStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 22/07/2024
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
        var event = new BlockOnInteractEvent(itemStack, dimension, interactInfo, false);
        manager.callEvent(event);
        if (itemStack instanceof ItemBoneMealStack) {
            grow();
        }
        return event.isSuccess();
    }

    public void grow() {

    }
}
