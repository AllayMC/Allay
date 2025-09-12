package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTuffBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTuffBehaviorImpl extends BlockBehaviorImpl implements BlockTuffBehavior {
    public BlockTuffBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
