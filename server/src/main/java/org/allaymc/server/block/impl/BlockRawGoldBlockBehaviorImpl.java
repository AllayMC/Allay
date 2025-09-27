package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRawGoldBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRawGoldBlockBehaviorImpl extends BlockBehaviorImpl implements BlockRawGoldBlockBehavior {
    public BlockRawGoldBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
