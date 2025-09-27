package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCoralBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCoralBlockBehaviorImpl extends BlockBehaviorImpl implements BlockCoralBlockBehavior {
    public BlockCoralBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
