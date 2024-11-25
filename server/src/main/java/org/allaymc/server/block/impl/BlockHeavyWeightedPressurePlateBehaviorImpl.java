package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHeavyWeightedPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockHeavyWeightedPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockHeavyWeightedPressurePlateBehavior {
    public BlockHeavyWeightedPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
