package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFenceGateBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockFenceGateBehaviorImpl extends BlockBehaviorImpl implements BlockFenceGateBehavior {
    public BlockFenceGateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
