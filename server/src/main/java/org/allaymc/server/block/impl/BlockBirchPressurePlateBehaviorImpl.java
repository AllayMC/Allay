package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBirchPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBirchPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockBirchPressurePlateBehavior {
    public BlockBirchPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
