package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWildflowersBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWildflowersBehaviorImpl extends BlockBehaviorImpl implements BlockWildflowersBehavior {
    public BlockWildflowersBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
