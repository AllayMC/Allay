package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTuffBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTuffBehaviorImpl extends BlockBehaviorImpl implements BlockTuffBehavior {
    public BlockTuffBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
