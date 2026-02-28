package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.ShelfContainerImpl;

/**
 * @author daoge_cmd
 */
public class BlockEntityShelfContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl {

    public BlockEntityShelfContainerHolderComponentImpl() {
        super(ShelfContainerImpl::new);
        this.openContainerOnInteract = false;
        this.saveEmptySlots = true;
    }

    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        for (int slot = 0; slot < container.getItemStackArray().length; slot++) {
            container.addSlotChangeListener(slot, itemStack ->
                    ((BlockEntityBaseComponentImpl) baseComponent).sendBlockEntityToViewers());
        }
    }
}
