package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockScaffoldingBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockScaffoldingBehaviorImpl extends BlockBehaviorImpl implements BlockScaffoldingBehavior {
    @Delegate
    private BlockFallableComponent fallableComponent;

    public BlockScaffoldingBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
