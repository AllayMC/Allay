package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFrameBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockFrameBehaviorImpl extends BlockBehaviorImpl implements BlockFrameBehavior {
    public BlockFrameBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
