package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChorusPlantBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChorusPlantBehaviorImpl extends BlockBehaviorImpl implements BlockChorusPlantBehavior {
    public BlockChorusPlantBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
