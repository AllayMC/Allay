package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedExposedCopperChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedExposedCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedExposedCopperChestBehavior {
    public BlockWaxedExposedCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
