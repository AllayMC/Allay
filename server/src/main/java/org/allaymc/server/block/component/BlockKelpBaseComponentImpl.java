package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.KELP_AGE;

/**
 * @author daoge_cmd
 */
public class BlockKelpBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final int MAX_AGE = 25;

    public BlockKelpBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        // Must be in water
        if (!isInWater(dimension, placeBlockPos)) {
            return false;
        }

        // Must be on solid block or kelp
        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        var downType = downState.getBlockType();
        if (downType != BlockTypes.KELP) {
            if (!downState.getBlockStateData().isSolid() ||
                downType == BlockTypes.MAGMA ||
                downType == BlockTypes.SOUL_SAND) {
                return false;
            }
        }

        // Random age 0-24
        var age = ThreadLocalRandom.current().nextInt(MAX_AGE);
        blockState = blockState.setPropertyValue(KELP_AGE, age);

        // If below is kelp, set its age to max
        if (downType == BlockTypes.KELP) {
            var downPos = BlockFace.DOWN.offsetPos(placeBlockPos);
            var newDownState = downState.setPropertyValue(KELP_AGE, MAX_AGE);
            dimension.setBlockState(downPos, newDownState);
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN) {
            var downType = neighbor.getBlockType();
            if (downType != BlockTypes.KELP && !neighbor.getBlockStateData().isSolid()) {
                block.breakBlock();
                return;
            }
            if (downType == BlockTypes.MAGMA || downType == BlockTypes.SOUL_SAND) {
                block.breakBlock();
            }
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var age = block.getPropertyValue(KELP_AGE);
        if (age >= MAX_AGE) {
            return;
        }

        // 15% growth chance
        if (ThreadLocalRandom.current().nextInt(100) >= 15) {
            return;
        }

        var abovePos = block.getPosition().add(0, 1, 0, new Vector3i());
        if (!isInWater(block.getDimension(), abovePos)) {
            return;
        }

        if (block.getDimension().getBlockState(abovePos).getBlockType() != BlockTypes.AIR &&
            !block.getDimension().getBlockState(abovePos).getBlockType().hasBlockTag(BlockTags.WATER)) {
            return;
        }

        // Set current kelp age to max, new kelp gets age+1
        var dimension = block.getDimension();

        var newState = blockType.getDefaultState().setPropertyValue(KELP_AGE, age + 1);
        var event = new BlockGrowEvent(block, newState);
        if (event.call()) {
            dimension.setBlockState(block.getPosition(), block.getBlockState().setPropertyValue(KELP_AGE, MAX_AGE));
            dimension.setBlockState(abovePos, event.getNewBlockState());
        }
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        // Randomize age of kelp below
        var belowPos = BlockFace.DOWN.offsetPos(block.getPosition());
        var belowState = block.getDimension().getBlockState(belowPos);
        if (belowState.getBlockType() == BlockTypes.KELP) {
            var newAge = ThreadLocalRandom.current().nextInt(MAX_AGE);
            var newState = belowState.setPropertyValue(KELP_AGE, newAge);
            block.getDimension().setBlockState(belowPos, newState);
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        // Find topmost kelp
        var pos = interactInfo.clickedBlockPos();
        var topPos = findTopKelp(dimension, pos);
        var topState = dimension.getBlockState(topPos);
        var topAge = topState.getPropertyValue(KELP_AGE);

        var abovePos = topPos.add(0, 1, 0, new Vector3i());
        if (!isInWater(dimension, abovePos)) {
            return false;
        }
        if (dimension.getBlockState(abovePos).getBlockType() != BlockTypes.AIR &&
            !dimension.getBlockState(abovePos).getBlockType().hasBlockTag(BlockTags.WATER)) {
            return false;
        }

        // Set top kelp age to max
        var newState = blockType.getDefaultState().setPropertyValue(KELP_AGE, Math.min(topAge + 1, MAX_AGE));
        var event = new BlockGrowEvent(
                new Block(topState, new Position3i(topPos, dimension)),
                newState
        );
        if (event.call()) {
            dimension.setBlockState(topPos, topState.setPropertyValue(KELP_AGE, MAX_AGE));
            dimension.setBlockState(abovePos, event.getNewBlockState());
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.KELP.createItemStack(1));
    }

    protected Vector3ic findTopKelp(Dimension dimension, Vector3ic pos) {
        var current = new Vector3i(pos);
        while (true) {
            var above = new Vector3i(current.x, current.y + 1, current.z);
            if (dimension.getBlockState(above).getBlockType() != BlockTypes.KELP) {
                return current;
            }
            current.set(above);
        }
    }

    protected boolean isInWater(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        if (liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER)) {
            return true;
        }
        // Also check if the block itself is water
        var blockState = dimension.getBlockState(pos);
        return blockState.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
