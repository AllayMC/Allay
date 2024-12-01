package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFrostedIceBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFrostedIceBehaviorImpl extends BlockBehaviorImpl implements BlockFrostedIceBehavior {
    public BlockFrostedIceBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
