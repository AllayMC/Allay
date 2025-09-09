package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockConcreteBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockConcreteBehaviorImpl extends BlockBehaviorImpl implements BlockConcreteBehavior {
    public BlockConcreteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
