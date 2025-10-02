package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperChainBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCopperChainBehaviorImpl extends BlockBehaviorImpl implements BlockCopperChainBehavior {
    public BlockCopperChainBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
