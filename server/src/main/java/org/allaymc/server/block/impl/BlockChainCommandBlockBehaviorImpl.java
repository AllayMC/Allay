package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChainCommandBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChainCommandBlockBehaviorImpl extends BlockBehaviorImpl implements BlockChainCommandBlockBehavior {
    public BlockChainCommandBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
