package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFernBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockFernBehaviorImpl extends BlockBehaviorImpl implements BlockFernBehavior {
    public BlockFernBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
