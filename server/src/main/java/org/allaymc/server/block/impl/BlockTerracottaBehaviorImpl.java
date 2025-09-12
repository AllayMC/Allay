package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTerracottaBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTerracottaBehaviorImpl extends BlockBehaviorImpl implements BlockTerracottaBehavior {
    public BlockTerracottaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
