package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockEndStoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockEndStoneBehaviorImpl extends BlockBehaviorImpl implements BlockEndStoneBehavior {
    public BlockEndStoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
