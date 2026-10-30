package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityJukebox;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemMusicDiscStack;
import org.allaymc.api.math.position.Position3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;

/**
 * {@code BlockJukeboxBaseComponentImpl} transfers music discs between a player and
 * a jukebox block entity and exposes the disc's comparator signal. Shared
 * component handlers receive the interaction before the jukebox-specific insertion
 * or ejection path runs. An occupied jukebox ejects its stored disc regardless of
 * the item used to interact.
 *
 * <p>
 * Ejection clears the stored disc and stops playback before dropping the old item.
 * Insertion stores the supplied disc reference, clears the player's hand, and
 * starts playback. These operations form an ordered transfer without rollback for
 * later failures. Breaking the jukebox runs shared break handling before
 * requesting that playback stop.
 *
 * @author IWareQ
 */
public class BlockJukeboxBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code blockEntityHolderComponent} resolves music-disc storage and playback.
     * Component initialization injects this holder before interaction, break, or
     * comparator calls reach the jukebox behavior.
     */
    @Dependency
    BlockBlockEntityHolderComponent<BlockEntityJukebox> blockEntityHolderComponent;

    /**
     * Creates the jukebox block component for the supplied registered block type.
     */
    public BlockJukeboxBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared handlers before inserting or ejecting a music disc.
     * {@code interactInfo} must identify the clicked jukebox and player. An
     * existing disc is removed from storage before playback stops and the old
     * stack drops into the world. Otherwise a music-disc input is stored by
     * reference before the player's entire hand is cleared and playback begins.
     * The sequence does not restore earlier changes if a later operation fails.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var blockEntity = blockEntityHolderComponent.getBlockEntity(new Position3i(interactInfo.clickedBlockPos(), dimension));
        var oldMusicDiscItem = blockEntity.getMusicDiscItem();
        if (oldMusicDiscItem != null) {
            // Remove the disc from storage before publishing its world drop so
            // subsequent jukebox interactions no longer retrieve the ejected
            // stack.
            blockEntity.setMusicDiscItem(null);
            blockEntity.stop();
            dimension.dropItem(oldMusicDiscItem, new Position3d(blockEntity.getPosition()).add(0.5, 1, 0.5));
            return true;
        } else if (itemStack instanceof ItemMusicDiscStack musicDiscItem) {
            // Store the same disc reference before clearing the hand and starting
            // playback; the jukebox becomes the owner of the transferred held
            // stack.
            blockEntity.setMusicDiscItem(musicDiscItem);
            interactInfo.player().clearItemInHand();
            blockEntity.play();
            return true;
        }

        return false;
    }

    /**
     * Runs shared break effects before stopping the resolved jukebox's playback.
     * The holder is queried after shared handling; a result that is not a jukebox
     * leaves no playback operation to perform.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (blockEntity instanceof BlockEntityJukebox jukebox) {
            jukebox.stop();
        }
    }

    /**
     * Returns whether this jukebox supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the stored music disc's configured comparator signal. A missing
     * jukebox or absent disc returns zero, allowing an empty jukebox to remain
     * distinguishable from a playing disc.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var jukebox = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (jukebox == null) {
            return 0;
        }
        var disc = jukebox.getMusicDiscItem();
        if (disc == null) {
            return 0;
        }
        return disc.getComparatorSignal();
    }
}
