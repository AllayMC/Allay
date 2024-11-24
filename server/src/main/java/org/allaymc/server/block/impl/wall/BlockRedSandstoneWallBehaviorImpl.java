package org.allaymc.server.block.impl.wall;

import java.util.List;
import org.allaymc.api.block.interfaces.wall.BlockRedSandstoneWallBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockRedSandstoneWallBehaviorImpl extends BlockBehaviorImpl implements BlockRedSandstoneWallBehavior {
    public BlockRedSandstoneWallBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
