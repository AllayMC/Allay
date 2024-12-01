package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCrimsonPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCrimsonPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockCrimsonPressurePlateBehavior {
    public BlockCrimsonPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
