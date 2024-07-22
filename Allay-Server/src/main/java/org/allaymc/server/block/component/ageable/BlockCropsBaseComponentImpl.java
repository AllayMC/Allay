package org.allaymc.server.block.component.ageable;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.GROWTH;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemBoneMealStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

import java.util.*;

@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "growth")
public class BlockCropsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCropsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockState) {
        //TODO: check groth condition
        var oldGrowth = blockState.blockState().getPropertyValue(GROWTH)
        if (oldGrowth < GROWTH.getMax()) {
            grow(blockState.blockState(), oldGrowth + 1);
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var event = new BlockOnInteractEvent(itemStack, dimension, interactInfo, false);
        manager.callEvent(event);

        var blockState = dimension.getBlockState(interactInfo.clickBlockPos());
        if (itemStack instanceof ItemBoneMealStack && blockState.getPropertyValue(GROWTH) < GROWTH.getMax()) {
            var random = new Random();
            int newAge = random.nextInt(3) + 2; //Between 2 and 5
            if (newAge > GROWTH.getMax()) {
                newAge = GROWTH.getMax();
            }
            grow(blockState, newAge);
        }

        return event.isSuccess();
    }

    public void grow(BlockState blockState, Integer newAge) {
        //TODO: event
        blockState.setProperty(GROWTH, newAge);
    }
}
