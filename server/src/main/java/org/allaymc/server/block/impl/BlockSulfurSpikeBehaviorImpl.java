package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockSulfurSpikeBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

public class BlockSulfurSpikeBehaviorImpl extends BlockBehaviorImpl implements BlockSulfurSpikeBehavior {
    public BlockSulfurSpikeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
