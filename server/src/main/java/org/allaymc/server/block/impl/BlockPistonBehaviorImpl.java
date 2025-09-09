package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPistonBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPistonBehaviorImpl extends BlockBehaviorImpl implements BlockPistonBehavior {
    public BlockPistonBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
