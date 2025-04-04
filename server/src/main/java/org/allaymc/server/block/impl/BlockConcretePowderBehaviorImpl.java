package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockConcretePowderBaseComponent;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockConcretePowderBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockConcretePowderBehaviorImpl extends BlockBehaviorImpl implements BlockConcretePowderBehavior {
    @Delegate
    private BlockFallableComponent fallableComponent;

    public BlockConcretePowderBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }

    @Delegate
    @Override
    public BlockConcretePowderBaseComponent getBaseComponent() {
        return (BlockConcretePowderBaseComponent) super.getBaseComponent();
    }
}
