package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNetherGoldOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockNetherGoldOreBehaviorImpl extends BlockBehaviorImpl implements BlockNetherGoldOreBehavior {
    public BlockNetherGoldOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
