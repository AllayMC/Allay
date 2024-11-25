package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGildedBlackstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGildedBlackstoneBehaviorImpl extends BlockBehaviorImpl implements BlockGildedBlackstoneBehavior {
    public BlockGildedBlackstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
