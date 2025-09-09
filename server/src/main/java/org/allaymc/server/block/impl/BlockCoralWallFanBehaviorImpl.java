package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCoralWallFanBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCoralWallFanBehaviorImpl extends BlockBehaviorImpl implements BlockCoralWallFanBehavior {
    public BlockCoralWallFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
