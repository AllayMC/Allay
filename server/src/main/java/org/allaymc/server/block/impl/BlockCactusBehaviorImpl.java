package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCactusBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCactusBehaviorImpl extends BlockBehaviorImpl implements BlockCactusBehavior {
    public BlockCactusBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
