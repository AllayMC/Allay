package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLiquidBehaviorImpl extends BlockBehaviorImpl implements BlockLiquidBehavior {
    public BlockLiquidBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
