package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGrindstoneBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGrindstoneBehaviorImpl extends BlockBehaviorImpl implements BlockGrindstoneBehavior {
    public BlockGrindstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
