package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCoalBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCoalBlockBehaviorImpl extends BlockBehaviorImpl implements BlockCoalBlockBehavior {
    public BlockCoalBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
