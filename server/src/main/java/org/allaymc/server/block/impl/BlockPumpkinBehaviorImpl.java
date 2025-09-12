package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPumpkinBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPumpkinBehaviorImpl extends BlockBehaviorImpl implements BlockPumpkinBehavior {
    public BlockPumpkinBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
