package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTwistingVinesBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTwistingVinesBehaviorImpl extends BlockBehaviorImpl implements BlockTwistingVinesBehavior {
    public BlockTwistingVinesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
