package org.allaymc.server.block.component.bamboo;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.BambooLeafSize;
import org.allaymc.api.block.property.enums.BambooStalkThickness;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author daoge_cmd
 */
public class BlockBambooSaplingBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockBambooSaplingBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }
        if (!canSupportBamboo(dimension, placeBlockPos)) {
            return false;
        }
        // Don't place in liquid
        var liquid = dimension.getLiquid(placeBlockPos).right();
        if (liquid != null) {
            return false;
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN) {
            if (!canSupportBamboo(block.getDimension(), block.getPosition())) {
                block.breakBlock();
                return;
            }
        }

        // If block above is bamboo, convert self to bamboo
        if (face == BlockFace.UP && neighbor.getBlockType() == BlockTypes.BAMBOO) {
            var bambooState = BlockTypes.BAMBOO.getDefaultState()
                    .setPropertyValue(BAMBOO_STALK_THICKNESS, BambooStalkThickness.THIN)
                    .setPropertyValue(BAMBOO_LEAF_SIZE, BambooLeafSize.NO_LEAVES);
            block.getDimension().setBlockState(block.getPosition(), bambooState);
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        if (ThreadLocalRandom.current().nextInt(3) != 0) {
            return;
        }

        if (block.getDimension().getLightEngine().getInternalLight(block.getPosition().add(0, 1, 0, new org.joml.Vector3i())) < 9) {
            return;
        }

        growBambooAbove(block);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        if (growBambooAbove(clickedBlock)) {
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.BAMBOO.createItemStack(1));
    }

    protected boolean growBambooAbove(Block block) {
        var abovePos = block.getPosition().add(0, 1, 0, new org.joml.Vector3i());
        if (block.getDimension().getBlockState(abovePos).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        var bambooState = BlockTypes.BAMBOO.getDefaultState()
                .setPropertyValue(BAMBOO_LEAF_SIZE, BambooLeafSize.SMALL_LEAVES)
                .setPropertyValue(BAMBOO_STALK_THICKNESS, BambooStalkThickness.THIN);

        var event = new BlockGrowEvent(block, bambooState);
        if (event.call()) {
            block.getDimension().setBlockState(abovePos, event.getNewBlockState());
            return true;
        }
        return false;
    }

    public static boolean canSupportBamboo(Dimension dimension, Vector3ic pos) {
        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        return downBlockType.hasBlockTag(BlockTags.DIRT) ||
               downBlockType.hasBlockTag(BlockTags.SAND) ||
               downBlockType == BlockTypes.BAMBOO ||
               downBlockType == BlockTypes.BAMBOO_SAPLING ||
               downBlockType == BlockTypes.MOSS_BLOCK ||
               downBlockType == BlockTypes.PODZOL ||
               downBlockType == BlockTypes.GRAVEL;
    }
}
