package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockSeaPickleBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSeaPickleBehaviorImpl extends BlockBehaviorImpl implements BlockSeaPickleBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockSeaPickleBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
