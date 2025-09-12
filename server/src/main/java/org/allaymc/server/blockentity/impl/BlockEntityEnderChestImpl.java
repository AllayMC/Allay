package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnderChest;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author IWareQ
 */
public class BlockEntityEnderChestImpl extends BlockEntityImpl implements BlockEntityEnderChest {
    public BlockEntityEnderChestImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public BlockEntityEnderChestBaseComponent getBaseComponent() {
        return (BlockEntityEnderChestBaseComponent) super.getBaseComponent();
    }
}
