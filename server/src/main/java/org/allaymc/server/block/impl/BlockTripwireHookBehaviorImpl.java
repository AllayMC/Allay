package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTripwireHookBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTripwireHookBehaviorImpl extends BlockBehaviorImpl implements BlockTripwireHookBehavior {
    public BlockTripwireHookBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
