package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDiamondBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDiamondBlockBehaviorImpl extends BlockBehaviorImpl implements BlockDiamondBlockBehavior {
    public BlockDiamondBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
