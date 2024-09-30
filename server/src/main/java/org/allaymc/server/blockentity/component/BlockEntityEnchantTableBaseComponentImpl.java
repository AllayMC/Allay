package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author daoge_cmd
 */
public class BlockEntityEnchantTableBaseComponentImpl extends BlockEntityBaseComponentImpl {
    @Dependency
    protected BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityEnchantTableBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }
}
