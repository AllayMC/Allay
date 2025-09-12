package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaterlilyBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWaterlilyBehaviorImpl extends BlockBehaviorImpl implements BlockWaterlilyBehavior {
    public BlockWaterlilyBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
