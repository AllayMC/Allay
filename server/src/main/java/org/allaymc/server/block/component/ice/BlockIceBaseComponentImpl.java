package org.allaymc.server.block.component.ice;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
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
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        if (isNeighborBlockLightBiggerThan(block.getDimension(), block.getPosition())) {
            var event = new BlockFadeEvent(block, BlockTypes.WATER.getDefaultState());
            if (event.call()) {
                block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
            }
        }
    }

    protected boolean isNeighborBlockLightBiggerThan(Dimension dimension, Vector3ic pos) {
        for (var face : BlockFace.VALUES) {
            if (dimension.getLightEngine().getBlockLight(face.offsetPos(pos)) > 11) {
                return true;
            }
        }

        return false;
    }


    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (block.offsetPos(BlockFace.DOWN).getBlockType() != BlockTypes.AIR) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.WATER.getDefaultState());
        }

        return Set.of();
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
