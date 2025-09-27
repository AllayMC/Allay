package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNetherWartBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockNetherWartBehaviorImpl extends BlockBehaviorImpl implements BlockNetherWartBehavior {
    public BlockNetherWartBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
