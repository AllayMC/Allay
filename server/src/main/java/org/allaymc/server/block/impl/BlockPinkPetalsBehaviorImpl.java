package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPinkPetalsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPinkPetalsBehaviorImpl extends BlockBehaviorImpl implements BlockPinkPetalsBehavior {
    public BlockPinkPetalsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
