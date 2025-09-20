package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStickyPistonArmCollisionBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockStickyPistonArmCollisionBehaviorImpl extends BlockBehaviorImpl implements BlockStickyPistonArmCollisionBehavior {
    public BlockStickyPistonArmCollisionBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
