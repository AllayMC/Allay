package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBeehiveBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBeehiveBehaviorImpl extends BlockBehaviorImpl implements BlockBeehiveBehavior {
    public BlockBeehiveBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
