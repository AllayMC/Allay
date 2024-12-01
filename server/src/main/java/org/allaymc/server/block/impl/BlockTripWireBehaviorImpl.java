package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTripWireBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTripWireBehaviorImpl extends BlockBehaviorImpl implements BlockTripWireBehavior {
    public BlockTripWireBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
