package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCoalOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCoalOreBehaviorImpl extends BlockBehaviorImpl implements BlockCoalOreBehavior {
    public BlockCoalOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
