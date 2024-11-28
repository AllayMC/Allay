package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDripstoneBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDripstoneBlockBehaviorImpl extends BlockBehaviorImpl implements BlockDripstoneBlockBehavior {
    public BlockDripstoneBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
