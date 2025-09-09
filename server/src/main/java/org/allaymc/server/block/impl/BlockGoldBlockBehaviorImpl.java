package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGoldBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGoldBlockBehaviorImpl extends BlockBehaviorImpl implements BlockGoldBlockBehavior {
    public BlockGoldBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
