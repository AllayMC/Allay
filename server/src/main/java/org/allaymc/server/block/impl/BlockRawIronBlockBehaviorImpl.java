package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRawIronBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRawIronBlockBehaviorImpl extends BlockBehaviorImpl implements BlockRawIronBlockBehavior {
    public BlockRawIronBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
