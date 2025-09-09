package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGraniteBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGraniteBehaviorImpl extends BlockBehaviorImpl implements BlockGraniteBehavior {
    public BlockGraniteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
