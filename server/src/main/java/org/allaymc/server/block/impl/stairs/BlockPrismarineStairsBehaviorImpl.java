package org.allaymc.server.block.impl.stairs;

import java.util.List;
import org.allaymc.api.block.interfaces.stairs.BlockPrismarineStairsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPrismarineStairsBehaviorImpl extends BlockBehaviorImpl implements BlockPrismarineStairsBehavior {
    public BlockPrismarineStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
