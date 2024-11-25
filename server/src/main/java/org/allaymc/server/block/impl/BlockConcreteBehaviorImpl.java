package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockConcreteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockConcreteBehaviorImpl extends BlockBehaviorImpl implements BlockConcreteBehavior {
    public BlockConcreteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
