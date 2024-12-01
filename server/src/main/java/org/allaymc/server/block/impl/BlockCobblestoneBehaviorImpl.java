package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCobblestoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCobblestoneBehaviorImpl extends BlockBehaviorImpl implements BlockCobblestoneBehavior {
    public BlockCobblestoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
