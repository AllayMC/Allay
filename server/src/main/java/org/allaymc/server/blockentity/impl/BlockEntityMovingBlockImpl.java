package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityMovingBlockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityMovingBlock;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Implementation of moving block block entity.
 *
 * @author daoge_cmd
 */
public class BlockEntityMovingBlockImpl extends BlockEntityImpl implements BlockEntityMovingBlock {

    @Delegate
    private BlockEntityMovingBlockBaseComponent movingBlockBaseComponent;

    public BlockEntityMovingBlockImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
