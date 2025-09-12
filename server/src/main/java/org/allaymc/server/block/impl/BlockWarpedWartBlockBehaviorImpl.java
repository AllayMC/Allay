package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWarpedWartBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWarpedWartBlockBehaviorImpl extends BlockBehaviorImpl implements BlockWarpedWartBlockBehavior {
    public BlockWarpedWartBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
