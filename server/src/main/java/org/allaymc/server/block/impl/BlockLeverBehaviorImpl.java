package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLeverBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLeverBehaviorImpl extends BlockBehaviorImpl implements BlockLeverBehavior {
    public BlockLeverBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
