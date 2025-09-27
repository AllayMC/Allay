package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedNetherBrickBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRedNetherBrickBehaviorImpl extends BlockBehaviorImpl implements BlockRedNetherBrickBehavior {
    public BlockRedNetherBrickBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
