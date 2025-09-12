package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHangingRootsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockHangingRootsBehaviorImpl extends BlockBehaviorImpl implements BlockHangingRootsBehavior {
    public BlockHangingRootsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
