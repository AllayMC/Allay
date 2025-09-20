package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGrassPathBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGrassPathBehaviorImpl extends BlockBehaviorImpl implements BlockGrassPathBehavior {
    public BlockGrassPathBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
