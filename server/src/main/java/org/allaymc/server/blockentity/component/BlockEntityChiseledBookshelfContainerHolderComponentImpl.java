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
 * @author daoge_cmd
 */
public class BlockEntityChiseledBookshelfContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl implements BlockEntityChiseledBookshelfContainerHolderComponent {

    private static final String TAG_LAST_INTERACTED_SLOT = "LastInteractedSlot";

    private int lastInteractedSlot;

    public BlockEntityChiseledBookshelfContainerHolderComponentImpl() {
        super(ChiseledBookshelfContainerImpl::new);
        this.openContainerOnInteract = false;
        this.saveEmptySlots = true;
    }

    @Override
    public int getLastInteractedSlot() {
        return lastInteractedSlot;
    }

    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        for (int slot = 0; slot < container.getItemStackArray().length; slot++) {
            final var changedSlot = slot;
            container.addSlotChangeListener(slot, itemStack -> onSlotChanged(changedSlot));
        }
        refreshState();
    }

    @EventHandler
    protected void saveLastInteractedSlot(CBlockEntitySaveNBTEvent event) {
        event.getNbt().putInt(TAG_LAST_INTERACTED_SLOT, lastInteractedSlot);
    }

    @EventHandler(priority = -1)
    protected void loadLastInteractedSlot(CBlockEntityLoadNBTEvent event) {
        lastInteractedSlot = Math.max(0, Math.min(6, event.getNbt().getInt(TAG_LAST_INTERACTED_SLOT, 0)));
        refreshState();
    }

    private void onSlotChanged(int slot) {
        lastInteractedSlot = slot + 1;
        refreshState();
        ((BlockEntityBaseComponentImpl) baseComponent).sendBlockEntityToViewers();
        baseComponent.getDimension().updateComparatorOutputLevel(baseComponent.getPosition());
    }

    private void refreshState() {
        var pos = baseComponent.getPosition();
        var dimension = pos.dimension();
        var booksStored = 0;
        var items = container.getItemStackArray();
        for (int slot = 0; slot < items.length; slot++) {
            if (items[slot] != ItemAirStack.AIR_STACK) {
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
