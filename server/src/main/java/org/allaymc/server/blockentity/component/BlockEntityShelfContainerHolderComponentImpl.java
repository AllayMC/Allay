package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.ShelfContainerImpl;

/**
 * {@code BlockEntityShelfContainerHolderComponentImpl} maintains a shelf's
 * inventory and publishes slot changes as block-entity updates. The shelf uses
 * direct interaction rather than a container window, and persistence includes
 * empty slots so saved data describes every shelf position.
 *
 * @author daoge_cmd
 */
public class BlockEntityShelfContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl {

    /**
     * Creates a shelf inventory with container-window opening disabled. Empty
     * slots are included in saved item data, preserving the complete
     * arrangement consumed by shelf block-entity updates and subsequent loads.
     */
    public BlockEntityShelfContainerHolderComponentImpl() {
        super(ShelfContainerImpl::new);
        this.openContainerOnInteract = false;
        this.saveEmptySlots = true;
    }

    /**
     * Installs comparator callbacks and block-entity delivery for each shelf
     * slot. Slot delivery uses the injected base component's current position
     * and requires a loaded chunk; later container replacement does not migrate
     * listeners.
     */
    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        for (int slot = 0; slot < container.getItemStackArray().length; slot++) {
            container.addSlotChangeListener(slot, itemStack ->
                    ((BlockEntityBaseComponentImpl) baseComponent).sendBlockEntityToViewers());
        }
    }
}
