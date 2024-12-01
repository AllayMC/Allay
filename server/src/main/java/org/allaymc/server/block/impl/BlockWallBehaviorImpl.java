package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWallBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWallBehaviorImpl extends BlockBehaviorImpl implements BlockWallBehavior {
    public BlockWallBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
