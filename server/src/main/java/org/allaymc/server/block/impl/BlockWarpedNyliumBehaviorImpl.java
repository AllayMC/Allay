package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWarpedNyliumBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWarpedNyliumBehaviorImpl extends BlockBehaviorImpl implements BlockWarpedNyliumBehavior {
    public BlockWarpedNyliumBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
