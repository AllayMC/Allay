package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGrassBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGrassBlockBehaviorImpl extends BlockBehaviorImpl implements BlockGrassBlockBehavior {
    public BlockGrassBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
