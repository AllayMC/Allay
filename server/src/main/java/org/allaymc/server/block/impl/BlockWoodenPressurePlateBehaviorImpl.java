package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWoodenPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWoodenPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockWoodenPressurePlateBehavior {
    public BlockWoodenPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
