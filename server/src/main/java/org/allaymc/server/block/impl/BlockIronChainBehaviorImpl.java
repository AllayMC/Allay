package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockIronChainBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockIronChainBehaviorImpl extends BlockBehaviorImpl implements BlockIronChainBehavior {
    public BlockIronChainBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
