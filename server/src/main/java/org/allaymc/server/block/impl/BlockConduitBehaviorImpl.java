package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockConduitBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockConduitBehaviorImpl extends BlockBehaviorImpl implements BlockConduitBehavior {
    public BlockConduitBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
