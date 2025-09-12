package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTurtleEggBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTurtleEggBehaviorImpl extends BlockBehaviorImpl implements BlockTurtleEggBehavior {
    public BlockTurtleEggBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
