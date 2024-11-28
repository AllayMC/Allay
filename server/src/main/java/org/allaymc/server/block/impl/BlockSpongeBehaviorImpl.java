package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSpongeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSpongeBehaviorImpl extends BlockBehaviorImpl implements BlockSpongeBehavior {
    public BlockSpongeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
