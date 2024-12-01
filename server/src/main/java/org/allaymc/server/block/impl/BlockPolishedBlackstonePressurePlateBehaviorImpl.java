package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedBlackstonePressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPolishedBlackstonePressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedBlackstonePressurePlateBehavior {
    public BlockPolishedBlackstonePressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
