package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWetSpongeBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWetSpongeBehaviorImpl extends BlockBehaviorImpl implements BlockWetSpongeBehavior {
    public BlockWetSpongeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
