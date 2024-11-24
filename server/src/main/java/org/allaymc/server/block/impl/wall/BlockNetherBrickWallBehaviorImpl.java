package org.allaymc.server.block.impl.wall;

import java.util.List;
import org.allaymc.api.block.interfaces.wall.BlockNetherBrickWallBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockNetherBrickWallBehaviorImpl extends BlockBehaviorImpl implements BlockNetherBrickWallBehavior {
    public BlockNetherBrickWallBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
