package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPressurePlateBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockPressurePlateBehavior {
    public BlockPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
