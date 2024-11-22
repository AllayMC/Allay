package org.allaymc.server.blockentity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockEntityShulkerBoxImpl extends BlockEntityImpl implements BlockEntityShulkerBox {
    @Delegate
    protected BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityShulkerBoxImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}