package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedMushroomBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRedMushroomBlockBehaviorImpl extends BlockBehaviorImpl implements BlockRedMushroomBlockBehavior {
    public BlockRedMushroomBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
