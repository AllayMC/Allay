package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWeepingVinesBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWeepingVinesBehaviorImpl extends BlockBehaviorImpl implements BlockWeepingVinesBehavior {
    public BlockWeepingVinesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
