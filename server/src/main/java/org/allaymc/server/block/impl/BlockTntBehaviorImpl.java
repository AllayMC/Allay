package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockTntBaseComponent;
import org.allaymc.api.block.interfaces.BlockTntBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTntBehaviorImpl extends BlockBehaviorImpl implements BlockTntBehavior {
    public BlockTntBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }

    @Delegate
    @Override
    public BlockTntBaseComponent getBaseComponent() {
        return (BlockTntBaseComponent) super.getBaseComponent();
    }
}
