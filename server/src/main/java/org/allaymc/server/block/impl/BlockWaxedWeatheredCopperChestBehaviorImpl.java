package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedWeatheredCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedWeatheredCopperChestBehavior {
    public BlockWaxedWeatheredCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
