package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLapisBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLapisBlockBehaviorImpl extends BlockBehaviorImpl implements BlockLapisBlockBehavior {
    public BlockLapisBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
