package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDripstoneBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDripstoneBlockBehaviorImpl extends BlockBehaviorImpl implements BlockDripstoneBlockBehavior {
    public BlockDripstoneBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
