package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPlantPile;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPlantPileImpl extends BlockBehaviorImpl implements BlockPlantPile {
    public BlockPlantPileImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
