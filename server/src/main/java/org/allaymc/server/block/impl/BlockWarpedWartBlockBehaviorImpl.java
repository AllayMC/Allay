package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWarpedWartBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWarpedWartBlockBehaviorImpl extends BlockBehaviorImpl implements BlockWarpedWartBlockBehavior {
    public BlockWarpedWartBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
