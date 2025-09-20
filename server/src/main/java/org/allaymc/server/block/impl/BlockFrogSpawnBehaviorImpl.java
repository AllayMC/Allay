package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFrogSpawnBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockFrogSpawnBehaviorImpl extends BlockBehaviorImpl implements BlockFrogSpawnBehavior {
    public BlockFrogSpawnBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
