package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBrewingStandBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBrewingStandBehaviorImpl extends BlockBehaviorImpl implements BlockBrewingStandBehavior {
    public BlockBrewingStandBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
