package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockRedSandBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRedSandBehaviorImpl extends BlockBehaviorImpl implements BlockRedSandBehavior {
    @Delegate
    private BlockFallableComponent fallableComponent;

    public BlockRedSandBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
