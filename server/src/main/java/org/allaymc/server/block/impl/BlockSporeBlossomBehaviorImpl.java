package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSporeBlossomBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSporeBlossomBehaviorImpl extends BlockBehaviorImpl implements BlockSporeBlossomBehavior {
    public BlockSporeBlossomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
