package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWeepingVinesBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWeepingVinesBehaviorImpl extends BlockBehaviorImpl implements BlockWeepingVinesBehavior {
    public BlockWeepingVinesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
