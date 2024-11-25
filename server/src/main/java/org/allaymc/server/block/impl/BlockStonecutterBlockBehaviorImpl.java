package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStonecutterBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockStonecutterBlockBehaviorImpl extends BlockBehaviorImpl implements BlockStonecutterBlockBehavior {
    public BlockStonecutterBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
