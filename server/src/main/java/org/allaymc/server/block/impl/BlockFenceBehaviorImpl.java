package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFenceBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockFenceBehaviorImpl extends BlockBehaviorImpl implements BlockFenceBehavior {
    public BlockFenceBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
