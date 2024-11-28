package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNetherBrickBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockNetherBrickBehaviorImpl extends BlockBehaviorImpl implements BlockNetherBrickBehavior {
    public BlockNetherBrickBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
