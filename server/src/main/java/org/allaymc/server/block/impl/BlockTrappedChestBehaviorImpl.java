package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTrappedChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTrappedChestBehaviorImpl extends BlockBehaviorImpl implements BlockTrappedChestBehavior {
    public BlockTrappedChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
