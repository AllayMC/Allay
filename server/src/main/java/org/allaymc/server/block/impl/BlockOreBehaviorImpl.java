package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOreBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockOreBehaviorImpl extends BlockBehaviorImpl implements BlockOreBehavior {
    public BlockOreBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
