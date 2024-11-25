package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFarmlandBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFarmlandBehaviorImpl extends BlockBehaviorImpl implements BlockFarmlandBehavior {
    public BlockFarmlandBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
