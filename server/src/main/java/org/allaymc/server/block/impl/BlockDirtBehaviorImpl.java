package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDirtBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDirtBehaviorImpl extends BlockBehaviorImpl implements BlockDirtBehavior {
    public BlockDirtBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
