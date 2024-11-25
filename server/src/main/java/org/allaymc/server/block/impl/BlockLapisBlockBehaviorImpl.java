package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLapisBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLapisBlockBehaviorImpl extends BlockBehaviorImpl implements BlockLapisBlockBehavior {
    public BlockLapisBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
