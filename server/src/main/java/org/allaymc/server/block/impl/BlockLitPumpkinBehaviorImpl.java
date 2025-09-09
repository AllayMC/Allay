package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLitPumpkinBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLitPumpkinBehaviorImpl extends BlockBehaviorImpl implements BlockLitPumpkinBehavior {
    public BlockLitPumpkinBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
