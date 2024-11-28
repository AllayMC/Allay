package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTerracottaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTerracottaBehaviorImpl extends BlockBehaviorImpl implements BlockTerracottaBehavior {
    public BlockTerracottaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
