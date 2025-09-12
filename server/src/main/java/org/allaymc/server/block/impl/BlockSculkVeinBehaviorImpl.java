package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSculkVeinBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSculkVeinBehaviorImpl extends BlockBehaviorImpl implements BlockSculkVeinBehavior {
    public BlockSculkVeinBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
