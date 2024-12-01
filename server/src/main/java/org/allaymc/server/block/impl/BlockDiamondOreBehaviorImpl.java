package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDiamondOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDiamondOreBehaviorImpl extends BlockBehaviorImpl implements BlockDiamondOreBehavior {
    public BlockDiamondOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
