package org.allaymc.server.block.component.ageable;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemBoneMealStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.type.BlockTypes.FARMLAND;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.GROWTH;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "growth")
public class BlockCropsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCropsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face != BlockFace.DOWN && face != BlockFace.UP) return true;
        var blockUnder = current.pos().dimension().getBlockState(BlockFace.DOWN.offsetPos(current.pos()));
        return this.canPlaceOnBlock(blockUnder.getBlockType());
    }

    @Override
    public boolean canPlaceOnBlock(BlockType<?> blockType) {
        return blockType == FARMLAND;
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

        if (itemStack instanceof ItemBoneMealStack) {
            var blockState = dimension.getBlockState(interactInfo.clickBlockPos());
            if (blockState.getPropertyValue(GROWTH) < GROWTH.getMax()) {
                int newAge = ThreadLocalRandom.current().nextInt(3) + 2; //Between 2 and 5
                grow(dimension, interactInfo.clickBlockPos(), newAge);
            }
        }

        return true;
    }

    public void grow(Dimension dimension, Vector3ic pos, Integer newAge) {
        if (newAge < GROWTH.getMin()) newAge = GROWTH.getMin();
        if (newAge > GROWTH.getMax()) newAge = GROWTH.getMax();

        //TODO: event
        updateBlockProperty(GROWTH, newAge, pos, dimension);
    }
}
