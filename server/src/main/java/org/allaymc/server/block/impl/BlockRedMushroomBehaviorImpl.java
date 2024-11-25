package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedMushroomBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRedMushroomBehaviorImpl extends BlockBehaviorImpl implements BlockRedMushroomBehavior {
    public BlockRedMushroomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
