package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.interfaces.BlockAnvilBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockAnvilBehaviorImpl extends BlockBehaviorImpl implements BlockAnvilBehavior {
    public BlockAnvilBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }

    @Delegate
    @Override
    public BlockAnvilBaseComponent getBaseComponent() {
        return (BlockAnvilBaseComponent) super.getBaseComponent();
    }
}
