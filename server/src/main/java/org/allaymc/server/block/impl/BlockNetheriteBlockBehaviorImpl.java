package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNetheriteBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockNetheriteBlockBehaviorImpl extends BlockBehaviorImpl implements BlockNetheriteBlockBehavior {
    public BlockNetheriteBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
