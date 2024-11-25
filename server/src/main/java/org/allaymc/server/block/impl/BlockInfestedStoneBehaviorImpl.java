package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockInfestedStoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockInfestedStoneBehaviorImpl extends BlockBehaviorImpl implements BlockInfestedStoneBehavior {
    public BlockInfestedStoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
