package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWarpedRootsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWarpedRootsBehaviorImpl extends BlockBehaviorImpl implements BlockWarpedRootsBehavior {
    public BlockWarpedRootsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
