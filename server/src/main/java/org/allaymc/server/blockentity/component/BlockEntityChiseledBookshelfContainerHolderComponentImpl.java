package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityChiseledBookshelfContainerHolderComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.ChiseledBookshelfContainerImpl;

/**
 * {@code BlockEntityChiseledBookshelfContainerHolderComponentImpl} maintains
 * bookshelf contents, last-interaction data, and the block's occupancy
 * property. Players interact with individual slots rather than opening a
 * container window, and saved items include empty slots so the inventory's full
 * shape is represented in NBT.
 *
 * <p>
 * Container indices run from zero, while {@code lastInteractedSlot} uses zero
 * for no interaction and one through six for actual slots. Slot notifications
 * update that encoding, rebuild the occupancy bit mask, and send current
 * block-entity data and comparator output. Loading clamps the stored
 * interaction value and explicitly refreshes occupancy because normal container
 * loading does not notify slot listeners. The refresh uses the base component's
 * position at the time the load handler runs.
 *
 * @author daoge_cmd
 */
public class BlockEntityChiseledBookshelfContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl implements BlockEntityChiseledBookshelfContainerHolderComponent {

    /**
     * {@code TAG_LAST_INTERACTED_SLOT} names the integer NBT value used to
     * preserve the bookshelf's last-interaction encoding across saves and
     * loads.
     */
    private static final String TAG_LAST_INTERACTED_SLOT = "LastInteractedSlot";

    /**
     * {@code lastInteractedSlot} stores zero before a recorded interaction;
     * slot callbacks store their zero-based index plus one for client encoding.
     */
    private int lastInteractedSlot;

    /**
     * Creates a bookshelf inventory with direct slot interaction and complete
     * slot saving. Container-window opening is disabled, and empty slots remain
     * in saved item data to represent the full inventory state.
     */
    public BlockEntityChiseledBookshelfContainerHolderComponentImpl() {
        super(ChiseledBookshelfContainerImpl::new);
        this.openContainerOnInteract = false;
        this.saveEmptySlots = true;
    }

    /**
     * Returns the last interacted slot in the one-based bookshelf encoding.
     */
    @Override
    public int getLastInteractedSlot() {
        return lastInteractedSlot;
    }

    /**
     * Installs comparator and bookshelf slot callbacks, then refreshes
     * occupancy. The injected base position must identify the bookshelf; later
     * container replacement does not migrate any callbacks registered on the
     * original inventory.
     */
    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        for (int slot = 0; slot < container.getItemStackArray().length; slot++) {
            final var changedSlot = slot;
            container.addSlotChangeListener(slot, itemStack -> onSlotChanged(changedSlot));
        }
        refreshState();
    }

    /**
     * Writes the current interaction encoding into the block-entity save
     * builder.
     */
    @EventHandler
    protected void saveLastInteractedSlot(CBlockEntitySaveNBTEvent event) {
        event.getNbt().putInt(TAG_LAST_INTERACTED_SLOT, lastInteractedSlot);
    }

    /**
     * Loads the interaction encoding and refreshes the bookshelf's occupancy
     * property. Missing data selects zero, and supplied values are clamped to
     * {@code 0..6}; the refresh uses the base component's current position.
     */
    @EventHandler(priority = -1)
    protected void loadLastInteractedSlot(CBlockEntityLoadNBTEvent event) {
        // Clamp persisted interaction values to the client encoding, whose zero
        // sentinel must remain distinct from the six actual bookshelf slots.
        lastInteractedSlot = Math.max(0, Math.min(6, event.getNbt().getInt(TAG_LAST_INTERACTED_SLOT, 0)));
        refreshState();
    }

    /**
     * Records a notified slot and publishes the bookshelf's updated visible
     * state. {@code slot} is zero-based; occupancy refresh precedes
     * block-entity delivery and comparator recalculation, and delivery requires
     * the containing chunk to be loaded.
     */
    private void onSlotChanged(int slot) {
        // Reserve zero for the absence of any recorded bookshelf interaction,
        // so client data distinguishes that state from each of the six actual
        // container slots.
        lastInteractedSlot = slot + 1;
        refreshState();
        ((BlockEntityBaseComponentImpl) baseComponent).sendBlockEntityToViewers();
        baseComponent.getDimension().updateComparatorOutputLevel(baseComponent.getPosition());
    }

    /**
     * Rebuilds the bookshelf occupancy property from the current inventory.
     * Each nonair stack contributes the bit for its zero-based slot, so the
     * block property reflects contents independently of the last-interaction
     * value.
     *
     * <p>
     * Loading items bypasses slot listeners, making an explicit refresh
     * necessary after load as well as after ordinary notifications. The method
     * leaves blocks without {@code BOOKS_STORED} unchanged and updates a
     * bookshelf only when the computed mask differs from its current property.
     */
    private void refreshState() {
        var pos = baseComponent.getPosition();
        var dimension = pos.dimension();
        var booksStored = 0;
        var items = container.getItemStackArray();
        for (int slot = 0; slot < items.length; slot++) {
            if (items[slot] != ItemAirStack.AIR_STACK) {
                // Encode each occupied slot as its corresponding bookshelf
                // property bit, keeping visible occupancy independent of the
                // last-interaction slot recorded for comparator behavior.
                booksStored |= 1 << slot;
            }
        }

        var blockState = dimension.getBlockState(pos);
        if (!blockState.getBlockType().hasProperty(BlockPropertyTypes.BOOKS_STORED)) {
            return;
        }

        if (blockState.getPropertyValue(BlockPropertyTypes.BOOKS_STORED) != booksStored) {
            dimension.updateBlockProperty(BlockPropertyTypes.BOOKS_STORED, booksStored, pos);
        }
    }
}
