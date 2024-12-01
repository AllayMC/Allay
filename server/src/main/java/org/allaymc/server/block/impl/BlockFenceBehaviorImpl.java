package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFenceBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFenceBehaviorImpl extends BlockBehaviorImpl implements BlockFenceBehavior {
    public BlockFenceBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
