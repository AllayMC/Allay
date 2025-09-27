package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockUnderwaterTntBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockUnderwaterTntBehaviorImpl extends BlockBehaviorImpl implements BlockUnderwaterTntBehavior {
    public BlockUnderwaterTntBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
