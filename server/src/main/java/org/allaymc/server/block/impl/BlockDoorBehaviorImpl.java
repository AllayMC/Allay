package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDoorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDoorBehaviorImpl extends BlockBehaviorImpl implements BlockDoorBehavior {
    public BlockDoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
