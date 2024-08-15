package org.allaymc.server.block.component.ageable;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.GROWTH;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemBoneMealStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "growth")
public class BlockCropsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCropsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockState) {
        //TODO: check growth condition
        var oldGrowth = blockState.blockState().getPropertyValue(GROWTH);
        if (oldGrowth < GROWTH.getMax()) {
            grow(blockState.pos().dimension(), blockState.pos(), oldGrowth + 1);
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;

        var blockState = dimension.getBlockState(interactInfo.clickBlockPos());
        if (itemStack instanceof ItemBoneMealStack && blockState.getPropertyValue(GROWTH) < GROWTH.getMax()) {
            int newAge = ThreadLocalRandom.current().nextInt(3) + 2; //Between 2 and 5
            if (newAge > GROWTH.getMax()) {
                newAge = GROWTH.getMax();
            }
            grow(dimension, interactInfo.clickBlockPos(), newAge);
        }

        return true;
    }

    public void grow(Dimension dimension, Vector3ic pos, Integer newAge) {
        //TODO: event
        updateBlockProperty(GROWTH, newAge, pos, dimension);
    }
}
