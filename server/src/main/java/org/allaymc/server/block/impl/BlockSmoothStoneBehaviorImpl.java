package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSmoothStoneBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSmoothStoneBehaviorImpl extends BlockBehaviorImpl implements BlockSmoothStoneBehavior {
    public BlockSmoothStoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
