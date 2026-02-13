package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCommandBlockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCommandBlock;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Command block block entity implementation.
 *
 * @author daoge_cmd
 */
public class BlockEntityCommandBlockImpl extends BlockEntityImpl implements BlockEntityCommandBlock {

    @Delegate
    private BlockEntityCommandBlockBaseComponent baseComponent;

    public BlockEntityCommandBlockImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
