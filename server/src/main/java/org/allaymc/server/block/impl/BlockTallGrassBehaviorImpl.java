package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTallGrassBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTallGrassBehaviorImpl extends BlockBehaviorImpl implements BlockTallGrassBehavior {
    public BlockTallGrassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
