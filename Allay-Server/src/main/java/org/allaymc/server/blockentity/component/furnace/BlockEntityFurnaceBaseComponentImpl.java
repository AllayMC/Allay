package org.allaymc.server.blockentity.component.furnace;

import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
public class BlockEntityFurnaceBaseComponentImpl extends BlockEntityBaseComponentImpl {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }


}
