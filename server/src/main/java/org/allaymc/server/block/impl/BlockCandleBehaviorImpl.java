package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCandleBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCandleBehaviorImpl extends BlockBehaviorImpl implements BlockCandleBehavior {
    public BlockCandleBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
