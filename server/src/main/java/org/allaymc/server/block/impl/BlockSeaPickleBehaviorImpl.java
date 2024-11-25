package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSeaPickleBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSeaPickleBehaviorImpl extends BlockBehaviorImpl implements BlockSeaPickleBehavior {
    public BlockSeaPickleBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
