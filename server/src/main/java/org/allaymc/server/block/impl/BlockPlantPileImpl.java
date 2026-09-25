package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockPlantPile;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPlantPileImpl extends BlockBehaviorImpl implements BlockPlantPile {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockPlantPileImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
