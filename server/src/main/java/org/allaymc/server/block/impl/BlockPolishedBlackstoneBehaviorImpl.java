package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedBlackstoneBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPolishedBlackstoneBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedBlackstoneBehavior {
    public BlockPolishedBlackstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
