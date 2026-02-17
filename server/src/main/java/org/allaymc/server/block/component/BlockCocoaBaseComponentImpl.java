package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_3;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;

/**
 * @author daoge_cmd
 */
public class BlockCocoaBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCocoaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        if (!placementInfo.blockFace().isHorizontal()) {
            return false;
        }

        var clickedBlock = placementInfo.getClickedBlock();
        if (!isJungleWood(clickedBlock.getBlockState())) {
            return false;
        }

        // Set direction based on the opposite of the clicked face
        blockState = blockState.setPropertyValue(DIRECTION_4, placementInfo.blockFace().opposite().getHorizontalIndex());

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        // Determine the face direction this cocoa is attached to
        var direction = block.getPropertyValue(DIRECTION_4);
        var attachedFace = getAttachedFace(direction);
        if (face == attachedFace) {
            if (!isJungleWood(neighbor.getBlockState())) {
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

        var age = block.getPropertyValue(AGE_3);
        if (age >= AGE_3.getMax()) {
            return;
        }

        if (ThreadLocalRandom.current().nextInt(2) != 0) {
            return;
        }

        var newState = block.getBlockState().setPropertyValue(AGE_3, age + 1);
        var event = new BlockGrowEvent(block, newState);
        if (event.call()) {
            block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
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

        var clickedBlock = interactInfo.getClickedBlock();
        var age = clickedBlock.getPropertyValue(AGE_3);
        if (age >= AGE_3.getMax()) {
            return false;
        }

        var newState = clickedBlock.getBlockState().setPropertyValue(AGE_3, age + 1);
        var event = new BlockGrowEvent(clickedBlock, newState);
        if (event.call()) {
            dimension.setBlockState(interactInfo.clickedBlockPos(), event.getNewBlockState());
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var age = block.getPropertyValue(AGE_3);
        int count = age < AGE_3.getMax() ? 1 : 3;
        return Set.of(ItemTypes.COCOA_BEANS.createItemStack(count));
    }

    protected boolean isJungleWood(BlockState blockState) {
        var type = blockState.getBlockType();
        return type.hasBlockTag(BlockTags.JUNGLE) && type.hasBlockTag(BlockTags.LOG);
    }

    protected BlockFace getAttachedFace(int direction) {
        // direction stores the opposite of the clicked face
        // e.g. click SOUTH face → direction = NORTH(2) → log is to NORTH
        return switch (direction) {
            case 0 -> BlockFace.SOUTH;
            case 1 -> BlockFace.WEST;
            case 2 -> BlockFace.NORTH;
            case 3 -> BlockFace.EAST;
            default -> BlockFace.SOUTH;
        };
    }
}
