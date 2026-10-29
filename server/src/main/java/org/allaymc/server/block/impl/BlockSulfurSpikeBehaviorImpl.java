package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSulfurSpikeBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSulfurSpikeBehaviorImpl extends BlockBehaviorImpl implements BlockSulfurSpikeBehavior {
    public BlockSulfurSpikeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
