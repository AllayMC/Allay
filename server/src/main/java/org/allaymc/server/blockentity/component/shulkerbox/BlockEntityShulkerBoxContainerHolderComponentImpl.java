package org.allaymc.server.blockentity.component.shulkerbox;

import org.allaymc.api.container.impl.ShulkerBoxContainer;
import org.allaymc.server.blockentity.component.BlockEntityContainerHolderComponentImpl;

/**
 * @author daoge_cmd
 */
public class BlockEntityShulkerBoxContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl {
    public BlockEntityShulkerBoxContainerHolderComponentImpl() {
        super(ShulkerBoxContainer::new);
    }

    @Override
    protected boolean dropItemWhenBreak() {
        return false;
    }
}
