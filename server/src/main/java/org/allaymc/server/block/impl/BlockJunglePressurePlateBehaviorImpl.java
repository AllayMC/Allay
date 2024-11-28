package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockJunglePressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockJunglePressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockJunglePressurePlateBehavior {
    public BlockJunglePressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
