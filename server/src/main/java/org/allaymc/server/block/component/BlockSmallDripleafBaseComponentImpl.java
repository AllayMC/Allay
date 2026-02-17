package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.BigDripleafTilt;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author daoge_cmd
 */
public class BlockSmallDripleafBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockSmallDripleafBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!canSurvive(dimension, placeBlockPos)) {
            return false;
        }

        var abovePos = new Vector3i(placeBlockPos).add(0, 1, 0);
        var aboveState = dimension.getBlockState(abovePos);
        if (aboveState.getBlockType() != BlockTypes.AIR && !aboveState.getBlockType().hasBlockTag(BlockTags.WATER)) {
            return false;
        }

        var processedState = processBlockProperties(blockState, placeBlockPos, placementInfo);
        var lowerState = processedState.setPropertyValue(UPPER_BLOCK_BIT, false);
        var upperState = processedState.setPropertyValue(UPPER_BLOCK_BIT, true);

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), lowerState, placementInfo) &&
               dimension.setBlockState(abovePos.x(), abovePos.y(), abovePos.z(), upperState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        var isUpper = block.getPropertyValue(UPPER_BLOCK_BIT);

        if (isUpper) {
            // Upper half: check lower half below
            if (face == BlockFace.DOWN) {
                var belowState = neighbor.getBlockState();
                if (belowState.getBlockType() != blockType || belowState.getPropertyValue(UPPER_BLOCK_BIT)) {
                    block.breakBlock();
                }
            }
        } else {
            // Lower half: check support below and upper half above
            if (face == BlockFace.DOWN) {
                if (!canSurvive(block.getDimension(), block.getPosition())) {
                    block.breakBlock();
                }
            } else if (face == BlockFace.UP) {
                var aboveState = neighbor.getBlockState();
                if (aboveState.getBlockType() != blockType || !aboveState.getPropertyValue(UPPER_BLOCK_BIT)) {
                    block.breakBlock();
                }
            }
        }
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        var isUpper = block.getPropertyValue(UPPER_BLOCK_BIT);
        var pos = block.getPosition();
        var dimension = block.getDimension();

        if (isUpper) {
            // Breaking upper half: also break lower half
            var belowPos = BlockFace.DOWN.offsetPos(pos);
            var belowState = dimension.getBlockState(belowPos);
            if (belowState.getBlockType() == blockType && !belowState.getPropertyValue(UPPER_BLOCK_BIT)) {
                dimension.breakBlock(belowPos, null, entity);
            }
        } else {
            // Breaking lower half: also break upper half
            var abovePos = new Vector3i(pos).add(0, 1, 0);
            var aboveState = dimension.getBlockState(abovePos);
            if (aboveState.getBlockType() == blockType && aboveState.getPropertyValue(UPPER_BLOCK_BIT)) {
                dimension.breakBlock(abovePos, null, entity);
            }
        }
    }

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        // Only drop from lower half
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            return false;
        }
        return super.isDroppable(block, usedItem, entity);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Only drops with shears
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return Set.of(ItemTypes.SMALL_DRIPLEAF_BLOCK.createItemStack());
        }
        return Set.of();
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);

        // Find the bottom-most small dripleaf in the column
        var bottomPos = new Vector3i(pos);
        if (blockState.getPropertyValue(UPPER_BLOCK_BIT)) {
            bottomPos.add(0, -1, 0);
        }
        // Walk down in case of stacked small dripleafs
        while (true) {
            var belowPos = new Vector3i(bottomPos).add(0, -1, 0);
            var belowState = dimension.getBlockState(belowPos);
            if (belowState.getBlockType() == blockType && !belowState.getPropertyValue(UPPER_BLOCK_BIT)) {
                bottomPos.set(belowPos);
            } else {
                break;
            }
        }

        var bottomState = dimension.getBlockState(bottomPos);
        var direction = bottomState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);

        // Random height: 2-5 blocks
        var random = ThreadLocalRandom.current();
        int height = random.nextInt(4) + 2;

        // Check if there's enough space
        for (int i = 0; i < height; i++) {
            var checkPos = new Vector3i(bottomPos).add(0, i, 0);
            var checkState = dimension.getBlockState(checkPos);
            var checkType = checkState.getBlockType();
            if (checkType != BlockTypes.AIR &&
                !checkType.hasBlockTag(BlockTags.WATER) &&
                checkType != blockType) {
                return false;
            }
        }

        // Replace with big dripleaf column
        var bigDripleafType = BlockTypes.BIG_DRIPLEAF;
        for (int i = 0; i < height; i++) {
            var placePos = new Vector3i(bottomPos).add(0, i, 0);
            BlockState newState;
            if (i < height - 1) {
                // Stem blocks
                newState = bigDripleafType.getDefaultState()
                        .setPropertyValue(BIG_DRIPLEAF_HEAD, false)
                        .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE)
                        .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction);
            } else {
                // Head block (top)
                newState = bigDripleafType.getDefaultState()
                        .setPropertyValue(BIG_DRIPLEAF_HEAD, true)
                        .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE)
                        .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction);
            }
            dimension.setBlockState(placePos, newState);
        }

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
        return true;
    }

    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        var belowPos = BlockFace.DOWN.offsetPos(pos);
        var belowState = dimension.getBlockState(belowPos);
        var belowType = belowState.getBlockType();

        // Clay is always valid
        if (belowType == BlockTypes.CLAY) {
            return true;
        }

        // Dirt-type blocks are valid only if the position is waterlogged
        if (belowType == BlockTypes.GRASS_BLOCK ||
            belowType == BlockTypes.DIRT ||
            belowType == BlockTypes.DIRT_WITH_ROOTS ||
            belowType == BlockTypes.MOSS_BLOCK) {
            return isInWater(dimension, pos);
        }

        // Another small dripleaf lower half below (for the upper half placement)
        if (belowType == blockType && !belowState.getPropertyValue(UPPER_BLOCK_BIT)) {
            return true;
        }

        return false;
    }

    protected boolean isInWater(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        if (liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER)) {
            return true;
        }
        var blockState = dimension.getBlockState(pos);
        return blockState.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
