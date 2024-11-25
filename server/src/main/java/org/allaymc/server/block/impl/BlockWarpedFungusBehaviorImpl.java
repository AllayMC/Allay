package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWarpedFungusBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWarpedFungusBehaviorImpl extends BlockBehaviorImpl implements BlockWarpedFungusBehavior {
    public BlockWarpedFungusBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
