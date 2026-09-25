package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockPotatoesBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPotatoesBehaviorImpl extends BlockBehaviorImpl implements BlockPotatoesBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockPotatoesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
