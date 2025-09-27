package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperChestBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWaxedWeatheredCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedWeatheredCopperChestBehavior {
    public BlockWaxedWeatheredCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
