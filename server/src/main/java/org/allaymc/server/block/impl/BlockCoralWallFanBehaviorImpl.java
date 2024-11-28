package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCoralWallFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCoralWallFanBehaviorImpl extends BlockBehaviorImpl implements BlockCoralWallFanBehavior {
    public BlockCoralWallFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
