package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChiseledPolishedBlackstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockChiseledPolishedBlackstoneBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledPolishedBlackstoneBehavior {
    public BlockChiseledPolishedBlackstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
