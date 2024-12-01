package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCoralBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCoralBehaviorImpl extends BlockBehaviorImpl implements BlockCoralBehavior {
    public BlockCoralBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
