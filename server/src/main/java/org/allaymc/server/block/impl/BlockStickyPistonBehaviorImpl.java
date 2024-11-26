package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStickyPistonBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockStickyPistonBehaviorImpl extends BlockBehaviorImpl implements BlockStickyPistonBehavior {
    public BlockStickyPistonBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
