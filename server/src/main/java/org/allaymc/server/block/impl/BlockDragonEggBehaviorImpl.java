package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDragonEggBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDragonEggBehaviorImpl extends BlockBehaviorImpl implements BlockDragonEggBehavior {
    public BlockDragonEggBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
