package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockCocoaBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCocoaBehaviorImpl extends BlockBehaviorImpl implements BlockCocoaBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockCocoaBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
