package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMobSpawnerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMobSpawnerBehaviorImpl extends BlockBehaviorImpl implements BlockMobSpawnerBehavior {
    public BlockMobSpawnerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
