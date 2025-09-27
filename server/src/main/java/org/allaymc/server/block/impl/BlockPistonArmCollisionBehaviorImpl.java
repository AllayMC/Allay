package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPistonArmCollisionBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPistonArmCollisionBehaviorImpl extends BlockBehaviorImpl implements BlockPistonArmCollisionBehavior {
    public BlockPistonArmCollisionBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
