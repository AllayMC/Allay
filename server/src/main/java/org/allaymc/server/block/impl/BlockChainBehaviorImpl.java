package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChainBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChainBehaviorImpl extends BlockBehaviorImpl implements BlockChainBehavior {
    public BlockChainBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
