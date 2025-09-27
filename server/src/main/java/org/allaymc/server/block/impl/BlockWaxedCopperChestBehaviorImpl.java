package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedCopperChestBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWaxedCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedCopperChestBehavior {
    public BlockWaxedCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
