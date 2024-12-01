package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDenyBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDenyBehaviorImpl extends BlockBehaviorImpl implements BlockDenyBehavior {
    public BlockDenyBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
