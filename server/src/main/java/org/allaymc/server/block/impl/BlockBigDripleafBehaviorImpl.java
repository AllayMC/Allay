package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockBigDripleafBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBigDripleafBehaviorImpl extends BlockBehaviorImpl implements BlockBigDripleafBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockBigDripleafBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
