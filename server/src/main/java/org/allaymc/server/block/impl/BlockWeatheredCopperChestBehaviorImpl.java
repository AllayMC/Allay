package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWeatheredCopperChestBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWeatheredCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockWeatheredCopperChestBehavior {
    public BlockWeatheredCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
