package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFenceGateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFenceGateBehaviorImpl extends BlockBehaviorImpl implements BlockFenceGateBehavior {
    public BlockFenceGateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
