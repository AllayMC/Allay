package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTallDryGrassBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTallDryGrassBehaviorImpl extends BlockBehaviorImpl implements BlockTallDryGrassBehavior {
    public BlockTallDryGrassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
