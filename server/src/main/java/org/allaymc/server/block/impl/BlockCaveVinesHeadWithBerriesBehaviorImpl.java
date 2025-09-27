package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCaveVinesHeadWithBerriesBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCaveVinesHeadWithBerriesBehaviorImpl extends BlockBehaviorImpl implements BlockCaveVinesHeadWithBerriesBehavior {
    public BlockCaveVinesHeadWithBerriesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
