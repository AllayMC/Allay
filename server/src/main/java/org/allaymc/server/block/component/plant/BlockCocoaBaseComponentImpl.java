package org.allaymc.server.block.component.plant;

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
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_3;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;

/**
 * {@code BlockCocoaBaseComponentImpl} implements jungle-log attachment, age growth, and
 * cocoa-bean drops. Player placement uses a horizontal log face and stores the direction back
 * toward the supporting log. Random growth and bone meal each propose a one-stage advance
 * through a cancellable growth event, while mature pods drop three beans instead of one.
 *
 * @author daoge_cmd
 */
public class BlockCocoaBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the cocoa behavior component for the supplied block type.
     */
    public BlockCocoaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places cocoa against a horizontal face of jungle-tagged log. A {@code null} {@code
     * placementInfo} delegates directly to inherited placement; player placement encodes the
     * opposite clicked face as the attachment direction before that delegation.
     */
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

        // Store the direction back toward the clicked log, rather than its outward
        // face, so neighbor updates later test the actual supporting block.
        blockState = blockState.setPropertyValue(DIRECTION_4, placementInfo.blockFace().opposite().getHorizontalIndex());

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and checks a changed supporting face. Cocoa
     * breaks when the state in its encoded attachment direction no longer has both the jungle
     * and log tags.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var direction = block.getPropertyValue(DIRECTION_4);
        var attachedFace = getAttachedFace(direction);
        if (face == attachedFace) {
            if (!isJungleWood(neighbor.getBlockState())) {
                block.breakBlock();
            }
        }
    }

    /**
     * Returns whether cocoa receives random updates for natural age growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Dispatches the inherited update and gives immature cocoa a one-half chance to advance
     * one age. A cancellable growth event can suppress the write or replace its state; mature
     * pods receive no proposal.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var age = block.getPropertyValue(AGE_3);
        if (age >= AGE_3.getMax()) {
            return;
        }

        // Random growth is throttled independently of guaranteed bone-meal stage attempts.
        if (ThreadLocalRandom.current().nextInt(2) != 0) {
            return;
        }

        var newState = block.getBlockState().setPropertyValue(AGE_3, age + 1);
        var event = new BlockGrowEvent(block, newState);
        if (event.call()) {
            block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
        }
    }

    /**
     * Attempts one cocoa age increase for bone meal after inherited interaction handling.
     * Mature pods and cancelled growth return {@code false}; accepted proposals write the
     * event state before consuming the held item and emitting particles.
     */
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

    /**
     * Returns one cocoa bean from an immature pod or three from a mature pod. The age
     * threshold alone chooses the count; this implementation does not apply Fortune to the
     * bean stack.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var age = block.getPropertyValue(AGE_3);
        int count = age < AGE_3.getMax() ? 1 : 3;
        return Set.of(ItemTypes.COCOA_BEANS.createItemStack(count));
    }

    /**
     * Returns whether the state has both jungle and log tags.
     */
    protected boolean isJungleWood(BlockState blockState) {
        var type = blockState.getBlockType();
        return type.hasBlockTag(BlockTags.JUNGLE) && type.hasBlockTag(BlockTags.LOG);
    }

    /**
     * Returns the supporting-log face encoded by the horizontal direction value. Values zero
     * through three map to south, west, north, and east; an unrecognized integer falls back to
     * south.
     */
    protected BlockFace getAttachedFace(int direction) {
        // Preserve the stored support direction when decoding the horizontal index;
        // unexpected indices use south rather than producing a missing face for neighbor checks.
        return switch (direction) {
            case 0 -> BlockFace.SOUTH;
            case 1 -> BlockFace.WEST;
            case 2 -> BlockFace.NORTH;
            case 3 -> BlockFace.EAST;
            default -> BlockFace.SOUTH;
        };
    }
}
