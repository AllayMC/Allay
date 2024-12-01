package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHopperBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockHopperBehaviorImpl extends BlockBehaviorImpl implements BlockHopperBehavior {
    public BlockHopperBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
