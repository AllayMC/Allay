package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityHeadBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityHead;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityHeadImpl extends BlockEntityImpl implements BlockEntityHead {
    public BlockEntityHeadImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public BlockEntityHeadBaseComponent getBaseComponent() {
        return (BlockEntityHeadBaseComponent) super.getBaseComponent();
    }
}
