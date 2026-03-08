package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.component.BlockLecternBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityLectern;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.block.LecternDropBookEvent;
import org.allaymc.api.eventbus.event.block.LecternPageTurnEvent;
import org.allaymc.api.eventbus.event.block.LecternPlaceBookEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;

import static org.allaymc.api.player.GameMode.CREATIVE;

/**
 * Implementation of lectern block behavior.
 *
 * @author daoge_cmd
 */
public class BlockLecternBaseComponentImpl extends BlockBaseComponentImpl implements BlockLecternBaseComponent {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityLectern> blockEntityHolderComponent;

    public BlockLecternBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        var player = interactInfo.player();
        var lectern = blockEntityHolderComponent.getBlockEntity(block.getPosition());

        if (lectern == null) {
            return false;
        }

        // If lectern has no book, try to place one
        if (!lectern.hasBook()) {
            if (itemStack.isEmptyOrAir()) {
                return false;
            }

            var itemType = itemStack.getItemType();
            if (itemType != ItemTypes.WRITTEN_BOOK && itemType != ItemTypes.WRITABLE_BOOK) {
                return false;
            }

            // Place book on lectern
            var bookToPlace = itemStack.copy();
            bookToPlace.setCount(1);

            // Call event
            var event = new LecternPlaceBookEvent(block, player, bookToPlace);
            event.call();
            if (event.isCancelled()) {
                return false;
            }

            if (player.getGameMode() != CREATIVE) {
                player.tryConsumeItemInHand();
            }

            lectern.setBook(bookToPlace);
            block.addSound(SimpleSound.LECTERN_BOOK_PLACE);

            return true;
        }

        // Lectern has a book - open reading interface
        if (player.isActualPlayer()) {
            player.getController().viewLectern(block.getPosition());
        }
        return true;
    }

    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        var lectern = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (lectern == null || !lectern.hasBook()) {
            return;
        }

        // Only players can drop books from lecterns
        if (!(entity instanceof EntityPlayer player)) {
            return;
        }

        // Drop book when punched (left-click)
        var event = new LecternDropBookEvent(block, player, lectern.getBook());
        event.call();
        if (!event.isCancelled()) {
            lectern.dropBook();
        }
    }

    /**
     * Called when a player turns a page in the lectern.
     * This is typically called from a network packet handler.
     *
     * @param block the lectern block
     * @param newPage the new page number
     */
    @Override
    public void onPageTurn(Block block, int newPage) {
        var lectern = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (lectern == null || !lectern.hasBook()) {
            return;
        }

        var oldPage = lectern.getCurrentPage();

        // Only process if page actually changed (prevents redstone pulse on close)
        if (oldPage == newPage) {
            return;
        }

        var event = new LecternPageTurnEvent(block, oldPage, newPage);
        event.call();
        if (event.isCancelled()) {
            return;
        }

        lectern.setCurrentPage(event.getNewPage());

        // Trigger redstone pulse
        executeRedstonePulse(block);
    }

    /**
     * Execute a redstone pulse from the lectern.
     */
    private void executeRedstonePulse(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Set powered bit to true
        dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, true, pos);

        // Schedule update to turn off the pulse
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, 4); // 4 ticks delay

        // Update redstone around
        dimension.updateAround(pos);
    }

    @Override
    public void onScheduledUpdate(Block block) {
        // Turn off the redstone pulse
        var dimension = block.getDimension();
        dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, false, block.getPosition());

        // Update redstone around
        dimension.updateAround(block.getPosition());
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var lectern = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (lectern == null || !lectern.hasBook()) {
            return 0;
        }

        var totalPages = lectern.getTotalPages();
        if (totalPages == 0) {
            return 0;
        }

        var currentPage = lectern.getCurrentPage();
        // Formula: 1 + floor((page / totalPages) * 14)
        // This gives a range of 1-15 based on page position
        return 1 + (int) ((float) currentPage / (totalPages - 1) * 14);
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(BlockPropertyTypes.POWERED_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        // Lectern strongly powers the block below it
        if (face == BlockFace.DOWN) {
            return getWeakPower(block, face);
        }
        return 0;
    }
}
