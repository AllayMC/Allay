package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBeeNestBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBeeNestBehaviorImpl extends BlockBehaviorImpl implements BlockBeeNestBehavior {
    public BlockBeeNestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
