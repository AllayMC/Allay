package org.allaymc.server.blockentity.component.shulkerbox;

import org.allaymc.server.blockentity.component.BlockEntityContainerHolderComponentImpl;
import org.allaymc.server.container.impl.ShulkerBoxContainerImpl;

/**
 * @author daoge_cmd
 */
public class BlockEntityShulkerBoxContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl {
    public BlockEntityShulkerBoxContainerHolderComponentImpl() {
        super(ShulkerBoxContainerImpl::new);
        this.dropItemOnBreak = false;
    }
}
