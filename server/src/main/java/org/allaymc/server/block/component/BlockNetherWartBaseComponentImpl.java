package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.FortuneDropHelper;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_4;

/**
 * @author daoge_cmd
 */
public class BlockNetherWartBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockNetherWartBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            if (placementInfo.blockFace() != BlockFace.UP) {
                return false;
            }
            if (placementInfo.getClickedBlock().getBlockType() != BlockTypes.SOUL_SAND) {
                return false;
            }
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && neighbor.getBlockType() != BlockTypes.SOUL_SAND) {
            block.breakBlock();
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var age = block.getPropertyValue(AGE_4);
        if (age >= AGE_4.getMax()) {
            return;
        }

        if (ThreadLocalRandom.current().nextInt(10) != 0) {
            return;
        }

        var newState = block.getBlockState().setPropertyValue(AGE_4, age + 1);
        var event = new BlockGrowEvent(block, newState);
        if (event.call()) {
            block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var age = block.getPropertyValue(AGE_4);
        if (age < AGE_4.getMax()) {
            return Set.of(ItemTypes.NETHER_WART.createItemStack(1));
        }
        // Mature: 2-4 with fortune (fortune adds to max, capped at 7)
        var count = FortuneDropHelper.discrete(usedItem, 2, 4, 7);
        return Set.of(ItemTypes.NETHER_WART.createItemStack(count));
    }
}
