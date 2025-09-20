package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPodzolBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPodzolBehaviorImpl extends BlockBehaviorImpl implements BlockPodzolBehavior {
    public BlockPodzolBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
