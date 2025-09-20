package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCrimsonRootsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCrimsonRootsBehaviorImpl extends BlockBehaviorImpl implements BlockCrimsonRootsBehavior {
    public BlockCrimsonRootsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
