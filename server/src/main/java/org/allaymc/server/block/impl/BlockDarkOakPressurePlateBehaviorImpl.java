package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDarkOakPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDarkOakPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockDarkOakPressurePlateBehavior {
    public BlockDarkOakPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
