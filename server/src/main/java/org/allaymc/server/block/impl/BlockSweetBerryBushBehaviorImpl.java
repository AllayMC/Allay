package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockSweetBerryBushBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSweetBerryBushBehaviorImpl extends BlockBehaviorImpl implements BlockSweetBerryBushBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockSweetBerryBushBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
