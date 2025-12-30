package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockConcretePowderBaseComponent;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockConcretePowderBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockConcretePowderBehaviorImpl extends BlockBehaviorImpl implements BlockConcretePowderBehavior {
    @Delegate
    private BlockFallableComponent fallableComponent;
    @Delegate
    private BlockConcretePowderBaseComponent concretePowderBaseComponent;

    public BlockConcretePowderBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
