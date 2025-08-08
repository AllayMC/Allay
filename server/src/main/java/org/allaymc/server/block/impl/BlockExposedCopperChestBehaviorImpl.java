package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockExposedCopperChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockExposedCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockExposedCopperChestBehavior {
    public BlockExposedCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
