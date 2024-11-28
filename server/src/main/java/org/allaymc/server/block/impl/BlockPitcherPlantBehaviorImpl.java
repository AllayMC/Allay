package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPitcherPlantBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPitcherPlantBehaviorImpl extends BlockBehaviorImpl implements BlockPitcherPlantBehavior {
    public BlockPitcherPlantBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
