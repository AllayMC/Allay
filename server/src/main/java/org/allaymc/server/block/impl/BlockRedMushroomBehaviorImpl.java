package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedMushroomBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRedMushroomBehaviorImpl extends BlockBehaviorImpl implements BlockRedMushroomBehavior {
    public BlockRedMushroomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
