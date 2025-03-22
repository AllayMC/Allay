package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockOreBehaviorImpl extends BlockBehaviorImpl implements BlockOreBehavior {
    public BlockOreBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
