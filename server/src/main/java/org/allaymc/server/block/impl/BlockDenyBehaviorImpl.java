package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDenyBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDenyBehaviorImpl extends BlockBehaviorImpl implements BlockDenyBehavior {
    public BlockDenyBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
