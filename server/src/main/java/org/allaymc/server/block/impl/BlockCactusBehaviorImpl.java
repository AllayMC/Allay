package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCactusBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCactusBehaviorImpl extends BlockBehaviorImpl implements BlockCactusBehavior {
    public BlockCactusBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
