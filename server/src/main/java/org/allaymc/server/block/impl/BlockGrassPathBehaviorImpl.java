package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGrassPathBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGrassPathBehaviorImpl extends BlockBehaviorImpl implements BlockGrassPathBehavior {
    public BlockGrassPathBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
