package org.allaymc.server.block.component.ice;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockIceBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockIceBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        super.onRandomUpdate(current);

        if (isNeighborBlockLightBiggerThan(current.getDimension(), current.getPos())) {
            var event = new BlockFadeEvent(current, BlockTypes.WATER.getDefaultState());
            if (event.call()) {
                current.getDimension().setBlockState(current.getPos(), event.getNewBlockState());
            }
        }
    }

    protected boolean isNeighborBlockLightBiggerThan(Dimension dimension, Vector3ic pos) {
        for (var face : BlockFace.values()) {
            if (dimension.getLightService().getBlockLight(face.offsetPos(pos)) > 11) {
                return true;
            }
        }

        return false;
    }


    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        if (current.offsetPos(BlockFace.DOWN).getBlockType() != BlockTypes.AIR) {
            current.getDimension().setBlockState(current.getPos(), BlockTypes.WATER.getDefaultState());
        }

        return Utils.EMPTY_ITEM_STACK_SET;
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
