package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAmethystClusterBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockAmethystClusterBehaviorImpl extends BlockBehaviorImpl implements BlockAmethystClusterBehavior {
    public BlockAmethystClusterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
