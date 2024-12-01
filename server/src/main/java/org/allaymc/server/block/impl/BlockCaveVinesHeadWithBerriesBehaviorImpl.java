package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCaveVinesHeadWithBerriesBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCaveVinesHeadWithBerriesBehaviorImpl extends BlockBehaviorImpl implements BlockCaveVinesHeadWithBerriesBehavior {
    public BlockCaveVinesHeadWithBerriesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
