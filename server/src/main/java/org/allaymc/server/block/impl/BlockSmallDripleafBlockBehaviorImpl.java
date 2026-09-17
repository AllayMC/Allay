package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockSmallDripleafBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSmallDripleafBlockBehaviorImpl extends BlockBehaviorImpl implements BlockSmallDripleafBlockBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockSmallDripleafBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
