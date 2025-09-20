package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCaveVinesBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCaveVinesBehaviorImpl extends BlockBehaviorImpl implements BlockCaveVinesBehavior {
    public BlockCaveVinesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
