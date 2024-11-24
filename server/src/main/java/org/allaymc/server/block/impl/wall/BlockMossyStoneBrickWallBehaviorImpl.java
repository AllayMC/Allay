package org.allaymc.server.block.impl.wall;

import java.util.List;
import org.allaymc.api.block.interfaces.wall.BlockMossyStoneBrickWallBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockMossyStoneBrickWallBehaviorImpl extends BlockBehaviorImpl implements BlockMossyStoneBrickWallBehavior {
    public BlockMossyStoneBrickWallBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
