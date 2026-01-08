package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityTargetBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityTarget;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Implementation of the target block entity.
 *
 * @author daoge_cmd
 */
public class BlockEntityTargetImpl extends BlockEntityImpl implements BlockEntityTarget {

    @Delegate
    private BlockEntityTargetBaseComponent targetBaseComponent;

    public BlockEntityTargetImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
