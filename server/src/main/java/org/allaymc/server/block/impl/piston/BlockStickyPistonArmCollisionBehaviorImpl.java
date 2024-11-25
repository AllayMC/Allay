package org.allaymc.server.block.impl.piston;

import org.allaymc.api.block.interfaces.piston.BlockStickyPistonArmCollisionBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockStickyPistonArmCollisionBehaviorImpl extends BlockBehaviorImpl implements BlockStickyPistonArmCollisionBehavior {
    public BlockStickyPistonArmCollisionBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
