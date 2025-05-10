package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDriedGhastBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDriedGhastBehaviorImpl extends BlockBehaviorImpl implements BlockDriedGhastBehavior {
    public BlockDriedGhastBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
