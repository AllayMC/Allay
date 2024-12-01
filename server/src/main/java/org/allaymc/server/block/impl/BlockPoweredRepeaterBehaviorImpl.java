package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPoweredRepeaterBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPoweredRepeaterBehaviorImpl extends BlockBehaviorImpl implements BlockPoweredRepeaterBehavior {
    public BlockPoweredRepeaterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
