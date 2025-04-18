package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBrickBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBrickBlockBehaviorImpl extends BlockBehaviorImpl implements BlockBrickBlockBehavior {
    public BlockBrickBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
