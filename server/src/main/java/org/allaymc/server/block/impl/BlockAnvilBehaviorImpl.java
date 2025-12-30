package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockAnvilBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockAnvilBehaviorImpl extends BlockBehaviorImpl implements BlockAnvilBehavior {
    @Delegate
    private BlockFallableComponent fallableComponent;
    @Delegate
    private BlockAnvilBaseComponent anvilBaseComponent;

    public BlockAnvilBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
