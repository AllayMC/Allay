package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockIronDoorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockIronDoorBehaviorImpl extends BlockBehaviorImpl implements BlockIronDoorBehavior {
    public BlockIronDoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
