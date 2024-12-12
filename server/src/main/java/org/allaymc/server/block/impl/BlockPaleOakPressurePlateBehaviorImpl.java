package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPaleOakPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPaleOakPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockPaleOakPressurePlateBehavior {
    public BlockPaleOakPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
