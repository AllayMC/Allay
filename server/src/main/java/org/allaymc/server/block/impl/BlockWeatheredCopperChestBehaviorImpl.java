package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWeatheredCopperChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWeatheredCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockWeatheredCopperChestBehavior {
    public BlockWeatheredCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
