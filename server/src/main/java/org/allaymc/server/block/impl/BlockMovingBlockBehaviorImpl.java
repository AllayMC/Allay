package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMovingBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockMovingBlockBehaviorImpl extends BlockBehaviorImpl implements BlockMovingBlockBehavior {
    public BlockMovingBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
