package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWetSpongeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWetSpongeBehaviorImpl extends BlockBehaviorImpl implements BlockWetSpongeBehavior {
    public BlockWetSpongeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
