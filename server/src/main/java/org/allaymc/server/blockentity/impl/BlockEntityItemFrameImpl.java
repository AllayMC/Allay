package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityItemFrameBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityGlowItemFrame;
import org.allaymc.api.blockentity.interfaces.BlockEntityItemFrame;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityItemFrameImpl extends BlockEntityImpl implements BlockEntityItemFrame, BlockEntityGlowItemFrame {

    @Delegate
    private BlockEntityItemFrameBaseComponent itemFrameBaseComponent;

    public BlockEntityItemFrameImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
