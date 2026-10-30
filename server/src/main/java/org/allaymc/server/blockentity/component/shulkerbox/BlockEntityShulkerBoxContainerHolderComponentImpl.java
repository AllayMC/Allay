package org.allaymc.server.blockentity.component.shulkerbox;

import org.allaymc.server.blockentity.component.BlockEntityContainerHolderComponentImpl;
import org.allaymc.server.container.impl.ShulkerBoxContainerImpl;

/**
 * {@code BlockEntityShulkerBoxContainerHolderComponentImpl} owns a shulker-box
 * inventory whose contents remain available during block replacement. The
 * ordinary holder replacement handler is disabled, so that handler neither
 * drops contents separately nor closes viewers or clears slots;
 * shulker-specific paths handle the retained data.
 *
 * @author daoge_cmd
 */
public class BlockEntityShulkerBoxContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl {
    /**
     * Creates a shulker-box container and disables ordinary replacement
     * dropping. This policy skips the inherited replacement handler entirely,
     * retaining its contents for the shulker-specific block-drop path rather
     * than clearing inventory.
     */
    public BlockEntityShulkerBoxContainerHolderComponentImpl() {
        super(ShulkerBoxContainerImpl::new);
        this.dropItemOnBreak = false;
    }
}
