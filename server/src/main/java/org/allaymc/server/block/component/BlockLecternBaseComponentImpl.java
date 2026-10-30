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
 * {@code BlockLecternBaseComponentImpl} handles lectern book insertion, player
 * ejection, and page changes. Book insertion copies a single item before
 * dispatching a cancellable event, while player punches can eject the retained
 * book through a separate event. Actual players can open a populated lectern's
 * reading interface.
 *
 * <p>
 * Page changes compare the requested page with the stored page before dispatch.
 * Accepted changes use the event's final page through the block entity's clamping
 * setter, then raise a four-tick redstone pulse. Comparator output uses the book's
 * page count and current index; the empty-book path produces zero, while ordinary
 * pages use the remaining index range to scale their signal.
 *
 * @author daoge_cmd
 */
public class BlockLecternBaseComponentImpl extends BlockBaseComponentImpl implements BlockLecternBaseComponent {

    /**
     * {@code blockEntityHolderComponent} resolves books, page indices, and lectern
     * updates. Component initialization injects this dependency before
     * interaction, page-turn, or comparator callbacks are used.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityLectern> blockEntityHolderComponent;

    /**
     * Creates the lectern block component for the supplied registered block type.
     */
    public BlockLecternBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a writable or written book on an empty lectern, or opens its reading
     * interface. Missing interaction data, unsupported items, or cancelled
     * placement returns {@code false}; accepted placement stores a single copied
     * book and consumes the held item outside creative mode.
     */
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

        if (!lectern.hasBook()) {
            if (itemStack.isEmptyOrAir()) {
                return false;
            }

            var itemType = itemStack.getItemType();
            if (itemType != ItemTypes.WRITTEN_BOOK && itemType != ItemTypes.WRITABLE_BOOK) {
                return false;
            }

            var bookToPlace = itemStack.copy();
            bookToPlace.setCount(1);

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

        if (player.isActualPlayer()) {
            player.getController().viewLectern(block.getPosition());
        }
        return true;
    }

    /**
     * Offers the lectern's book for ejection when punched by a player. Missing
     * books and nonplayer entities do nothing; an uncancelled
     * {@code LecternDropBookEvent} delegates the drop to the block entity.
     */
    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        var lectern = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (lectern == null || !lectern.hasBook()) {
            return;
        }

        if (!(entity instanceof EntityPlayer player)) {
            return;
        }

        var event = new LecternDropBookEvent(block, player, lectern.getBook());
        event.call();
        if (!event.isCancelled()) {
            lectern.dropBook();
        }
    }

    /**
     * Applies an event-approved page change and emits a redstone pulse. An empty
     * lectern or unchanged requested page is ignored before dispatch; cancellation
     * preserves the current page, and the block entity clamps the event's final
     * page.
     */
    @Override
    public void onPageTurn(Block block, int newPage) {
        var lectern = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (lectern == null || !lectern.hasBook()) {
            return;
        }

        var oldPage = lectern.getCurrentPage();

        // Closing the reading interface can repeat its current page. Skip that
        // request before dispatch so closing alone does not create a redstone
        // pulse.
        if (oldPage == newPage) {
            return;
        }

        var event = new LecternPageTurnEvent(block, oldPage, newPage);
        event.call();
        if (event.isCancelled()) {
            return;
        }

        lectern.setCurrentPage(event.getNewPage());

        executeRedstonePulse(block);
    }

    /**
     * Raises the lectern's powered bit and schedules its reset after four ticks.
     * Neighbor updates follow scheduling so connected redstone observes the raised
     * bit before the scheduled reset.
     */
    private void executeRedstonePulse(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, true, pos);

        // Keep the powered bit raised for four ticks before its scheduled reset,
        // allowing connected redstone to observe the page-turn pulse.
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, 4); // 4 ticks delay

        dimension.updateAround(pos);
    }

    /**
     * Clears the lectern's powered bit and notifies surrounding blocks. The
     * callback resets the current state directly, so every scheduled pulse-reset
     * request can end a later overlapping pulse.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        var dimension = block.getDimension();
        dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, false, block.getPosition());

        dimension.updateAround(block.getPosition());
    }

    /**
     * Returns whether this lectern supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Calculates comparator output from the current zero-based page index and
     * total page count. An absent book or zero pages returns zero. Other counts
     * use the floating-point ratio over {@code totalPages - 1} before integer
     * conversion; the one-page case therefore follows Java's conversion of the
     * resulting nonfinite value rather than an explicit special-case branch. The
     * built-in page setter normally clamps indices to the available range.
     */
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
        // Use the last zero-based index as the scale denominator; integer
        // conversion truncates the fractional signal before the one-point base is
        // added.
        return 1 + (int) ((float) currentPage / (totalPages - 1) * 14);
    }

    /**
     * Returns full redstone power while the lectern's powered bit is set.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(BlockPropertyTypes.POWERED_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    /**
     * Returns the pulse strength only for the downward-facing query. Other
     * directions return zero, so the lectern strongly powers the block below while
     * weak power remains available on every face.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (face == BlockFace.DOWN) {
            return getWeakPower(block, face);
        }
        return 0;
    }
}
