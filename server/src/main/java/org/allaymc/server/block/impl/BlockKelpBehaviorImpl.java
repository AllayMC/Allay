package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockKelpBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockKelpBehaviorImpl extends BlockBehaviorImpl implements BlockKelpBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockKelpBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
