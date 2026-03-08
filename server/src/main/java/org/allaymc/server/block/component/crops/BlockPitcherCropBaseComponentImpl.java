package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.event.CBlockRandomUpdateEvent;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * @author daoge_cmd
 */
public class BlockPitcherCropBaseComponentImpl extends BlockCropsBaseComponentImpl {

    public static final int MAX_GROWTH = 4;
    public static final int DOUBLE_THRESHOLD = 3;

    public BlockPitcherCropBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    protected boolean onBoneMealUsed(Dimension dimension, Vector3ic pos, BlockState crop) {
        // If upper half is clicked, redirect to lower half
        if (crop.getPropertyValue(UPPER_BLOCK_BIT)) {
            var lowerPos = new Vector3i(pos.x(), pos.y() - 1, pos.z());
            var lowerState = dimension.getBlockState(lowerPos);
            if (lowerState.getBlockType() != blockType) {
                return false;
            }
            return onBoneMealUsed(dimension, lowerPos, lowerState);
        }

        var growth = crop.getPropertyValue(GROWTH);
        if (growth >= MAX_GROWTH) {
            return false;
        }

        var newGrowth = growth + 1;

        // If becoming double, check space above is air or already pitcher crop upper half
        if (newGrowth >= DOUBLE_THRESHOLD) {
            var aboveState = dimension.getBlockState(pos.x(), pos.y() + 1, pos.z());
            var aboveType = aboveState.getBlockType();
            if (aboveType != BlockTypes.AIR && !(aboveType == blockType && aboveState.getPropertyValue(UPPER_BLOCK_BIT))) {
                return false;
            }
        }

        var newCrop = crop.setPropertyValue(GROWTH, newGrowth);
        var event = new BlockGrowEvent(new Block(crop, new Position3i(pos, dimension)), newCrop);
        if (event.call()) {
            dimension.setBlockState(pos, event.getNewBlockState());
            if (newGrowth >= DOUBLE_THRESHOLD) {
                var upperState = event.getNewBlockState().setPropertyValue(UPPER_BLOCK_BIT, true);
                dimension.setBlockState(pos.x(), pos.y() + 1, pos.z(), upperState);
            }
            return true;
        }

        return false;
    }

    @Override
    public void onRandomUpdate(Block block) {
        manager.callEvent(new CBlockRandomUpdateEvent(block));

        // Only lower half processes random updates
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            return;
        }

        if (block.getDimension().getLightEngine().getInternalLight(block.getPosition()) < 8) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                block.breakBlock();
            }
            return;
        }

        var growth = block.getPropertyValue(GROWTH);
        if (growth >= MAX_GROWTH) {
            return;
        }

        if (ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(block)) {
            var newGrowth = growth + 1;

            // If becoming double, check space above is air or already pitcher crop upper half
            if (newGrowth >= DOUBLE_THRESHOLD) {
                var above = block.offsetPos(BlockFace.UP);
                if (!above.isAir() && !(above.getBlockType() == blockType && above.getPropertyValue(UPPER_BLOCK_BIT))) {
                    return;
                }
            }

            var newCrop = block.setPropertyValue(GROWTH, newGrowth);
            var event = new BlockGrowEvent(block, newCrop.getBlockState());
            if (event.call()) {
                var pos = block.getPosition();
                block.getDimension().setBlockState(pos, event.getNewBlockState());
                if (newGrowth >= DOUBLE_THRESHOLD) {
                    var upperState = event.getNewBlockState().setPropertyValue(UPPER_BLOCK_BIT, true);
                    block.getDimension().setBlockState(pos.x(), pos.y() + 1, pos.z(), upperState);
                }
            }
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            // Upper half
            if (face == BlockFace.DOWN) {
                // Break if below is not pitcher crop lower half
                if (neighbor.getBlockType() != blockType || neighbor.getPropertyValue(UPPER_BLOCK_BIT)) {
                    block.breakBlock();
                }
            }
        } else {
            // Lower half
            if (face == BlockFace.DOWN) {
                // Break if below is not farmland
                if (neighbor.getBlockType() != BlockTypes.FARMLAND) {
                    block.breakBlock();
                }
            } else if (face == BlockFace.UP && block.getPropertyValue(GROWTH) >= DOUBLE_THRESHOLD) {
                // Break if above is not pitcher crop upper half
                if (neighbor.getBlockType() != blockType || !neighbor.getPropertyValue(UPPER_BLOCK_BIT)) {
                    block.breakBlock();
                }
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var growth = block.getPropertyValue(GROWTH);
        if (growth >= MAX_GROWTH) {
            return Set.of(ItemTypes.PITCHER_PLANT.createItemStack());
        }
        return Set.of(ItemTypes.PITCHER_POD.createItemStack());
    }

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            return false;
        }
        return super.isDroppable(block, usedItem, entity);
    }
}
