package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFrameBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFrameBehaviorImpl extends BlockBehaviorImpl implements BlockFrameBehavior {
    public BlockFrameBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
