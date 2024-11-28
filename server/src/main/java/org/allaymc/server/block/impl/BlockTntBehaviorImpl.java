package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTntBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTntBehaviorImpl extends BlockBehaviorImpl implements BlockTntBehavior {
    public BlockTntBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
