package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMossyCobblestoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMossyCobblestoneBehaviorImpl extends BlockBehaviorImpl implements BlockMossyCobblestoneBehavior {
    public BlockMossyCobblestoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
