package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.component.BlockEntitySignBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityHangingSign;
import org.allaymc.api.blockentity.interfaces.BlockEntitySign;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntitySignImpl extends BlockEntityImpl implements BlockEntitySign, BlockEntityHangingSign {
    public BlockEntitySignImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public BlockEntitySignBaseComponent getBaseComponent() {
        return (BlockEntitySignBaseComponent) super.getBaseComponent();
    }
}
