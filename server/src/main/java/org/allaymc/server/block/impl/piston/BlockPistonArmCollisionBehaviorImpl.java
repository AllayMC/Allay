package org.allaymc.server.block.impl.piston;

import org.allaymc.api.block.interfaces.piston.BlockPistonArmCollisionBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPistonArmCollisionBehaviorImpl extends BlockBehaviorImpl implements BlockPistonArmCollisionBehavior {
    public BlockPistonArmCollisionBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
