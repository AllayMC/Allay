package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLightWeightedPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLightWeightedPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockLightWeightedPressurePlateBehavior {
    public BlockLightWeightedPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
