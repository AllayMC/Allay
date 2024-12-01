package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockConcretePowderBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockConcretePowderBehaviorImpl extends BlockBehaviorImpl implements BlockConcretePowderBehavior {
    public BlockConcretePowderBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
