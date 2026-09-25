package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockWarpedFungusBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWarpedFungusBehaviorImpl extends BlockBehaviorImpl implements BlockWarpedFungusBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockWarpedFungusBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
