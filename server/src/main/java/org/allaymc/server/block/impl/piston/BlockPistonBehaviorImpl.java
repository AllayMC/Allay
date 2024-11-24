package org.allaymc.server.block.impl.piston;

import java.util.List;
import org.allaymc.api.block.interfaces.piston.BlockPistonBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPistonBehaviorImpl extends BlockBehaviorImpl implements BlockPistonBehavior {
    public BlockPistonBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
