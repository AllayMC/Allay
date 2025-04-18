package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFernBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFernBehaviorImpl extends BlockBehaviorImpl implements BlockFernBehavior {
    public BlockFernBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
