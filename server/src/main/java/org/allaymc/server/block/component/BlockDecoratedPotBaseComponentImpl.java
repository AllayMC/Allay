package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.component.BlockEntityDecoratedPotBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDecoratedPot;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.DecoratedPotInsertedSound;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * {@code BlockDecoratedPotBaseComponentImpl} handles pot orientation, item insertion,
 * comparator input, and decorated item drops. Pot drops copy sherd data from a
 * compatible block entity, while an unavailable or incompatible entity falls back to
 * an undecorated pot item.
 *
 * @author IWareQ
 */
public class BlockDecoratedPotBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the decorated pot block behavior for the supplied block type.
     */
    public BlockDecoratedPotBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the pot opposite the player's horizontal direction when player context
     * is available. A {@code null} {@code placementInfo} writes the supplied state
     * without modifying its orientation property.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var face = placementInfo.player().getHorizontalFace().opposite();
        // Decorated pot DIRECTION_4 uses horizontal face indices. Keep that ordering
        // distinct from the separate east-west-south-north mapping used for stairs
        // and trapdoors.
        blockState = blockState.setPropertyValue(BlockPropertyTypes.DIRECTION_4, face.getHorizontalIndex());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Offers unhandled nonempty item interactions to the pot's insertion logic.
     * Successful insertion consumes a held item outside creative mode and plays a
     * fill-progress sound; failed insertion still reports the interaction handled.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (itemStack.isEmptyOrAir()) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();

        var decoratedPot = clickedBlock.<BlockEntityDecoratedPot>getBlockEntity();
        if (decoratedPot.tryInsertItem(itemStack)) {
            if (player.getGameMode() != GameMode.CREATIVE) {
                player.tryConsumeItemInHand();
            }

            // The stack-size ratio supplies insertion sound pitch progress.
            var progress = (double) decoratedPot.getItem().getCount() / BlockEntityDecoratedPotBaseComponent.MAX_STACK_SIZE;
            clickedBlock.addSound(new DecoratedPotInsertedSound(progress));
        } else {
            clickedBlock.addSound(SimpleSound.DECORATED_POT_INSERT_FAILED);
        }

        return true;
    }

    /**
     * Returns whether decorated pots always support specialized comparator input.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the pot block entity's comparator output for this block. A missing
     * entity produces {@code 0}, so callers receive an empty signal when no pot
     * storage is available.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var decoratedPot = block.<BlockEntityDecoratedPot>getBlockEntity();
        if (decoratedPot == null) {
            return 0;
        }

        return decoratedPot.getComparatorOutput();
    }

    /**
     * Returns the decorated pot item selected from this block entity.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(this.createDecoratedPotDrop(block));
    }

    /**
     * Returns the same decorated pot item used for ordinary drops.
     */
    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return this.createDecoratedPotDrop(block);
    }

    /**
     * Creates a pot item carrying sherds from a decorated pot entity. A missing or
     * incompatible block entity selects a plain pot item instead of attaching
     * decoration data.
     */
    protected ItemStack createDecoratedPotDrop(Block block) {
        var blockEntity = block.getBlockEntity();
        if (blockEntity instanceof BlockEntityDecoratedPot decoratedPot) {
            var item = ItemTypes.DECORATED_POT.createItemStack(1);
            item.setSherds(decoratedPot.getSherds());
            return item;
        }

        // Without a compatible pot entity, the item still represents the pot block
        // but cannot include entity-owned sherd decorations.
        return ItemTypes.DECORATED_POT.createItemStack(1);
    }
}
