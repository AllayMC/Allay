package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGildedBlackstoneBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGildedBlackstoneBehaviorImpl extends BlockBehaviorImpl implements BlockGildedBlackstoneBehavior {
    public BlockGildedBlackstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
