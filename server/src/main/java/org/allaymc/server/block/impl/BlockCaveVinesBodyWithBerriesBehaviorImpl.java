package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCaveVinesBodyWithBerriesBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCaveVinesBodyWithBerriesBehaviorImpl extends BlockBehaviorImpl implements BlockCaveVinesBodyWithBerriesBehavior {
    public BlockCaveVinesBodyWithBerriesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
