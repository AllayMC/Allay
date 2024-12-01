package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBlackstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBlackstoneBehaviorImpl extends BlockBehaviorImpl implements BlockBlackstoneBehavior {
    public BlockBlackstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
