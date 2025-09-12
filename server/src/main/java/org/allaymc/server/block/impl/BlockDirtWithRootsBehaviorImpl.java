package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDirtWithRootsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDirtWithRootsBehaviorImpl extends BlockBehaviorImpl implements BlockDirtWithRootsBehavior {
    public BlockDirtWithRootsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
