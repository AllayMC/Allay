package org.allaymc.server.block.impl.piston;

import org.allaymc.api.block.interfaces.piston.BlockPistonBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPistonBehaviorImpl extends BlockBehaviorImpl implements BlockPistonBehavior {
    public BlockPistonBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
