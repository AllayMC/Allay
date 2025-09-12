package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCandleCakeBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCandleCakeBehaviorImpl extends BlockBehaviorImpl implements BlockCandleCakeBehavior {
    public BlockCandleCakeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
