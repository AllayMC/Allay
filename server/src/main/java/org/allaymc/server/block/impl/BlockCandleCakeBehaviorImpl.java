package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCandleCakeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCandleCakeBehaviorImpl extends BlockBehaviorImpl implements BlockCandleCakeBehavior {
    public BlockCandleCakeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
