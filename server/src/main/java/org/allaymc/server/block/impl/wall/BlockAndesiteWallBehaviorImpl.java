package org.allaymc.server.block.impl.wall;

import java.util.List;
import org.allaymc.api.block.interfaces.wall.BlockAndesiteWallBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockAndesiteWallBehaviorImpl extends BlockBehaviorImpl implements BlockAndesiteWallBehavior {
    public BlockAndesiteWallBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
