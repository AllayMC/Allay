package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockGrassBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGrassBlockBehaviorImpl extends BlockBehaviorImpl implements BlockGrassBlockBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockGrassBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
