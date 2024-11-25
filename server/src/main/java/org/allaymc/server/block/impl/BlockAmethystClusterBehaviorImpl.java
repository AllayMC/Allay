package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAmethystClusterBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockAmethystClusterBehaviorImpl extends BlockBehaviorImpl implements BlockAmethystClusterBehavior {
    public BlockAmethystClusterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
