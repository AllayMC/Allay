package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHayBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockHayBlockBehaviorImpl extends BlockBehaviorImpl implements BlockHayBlockBehavior {
    public BlockHayBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
