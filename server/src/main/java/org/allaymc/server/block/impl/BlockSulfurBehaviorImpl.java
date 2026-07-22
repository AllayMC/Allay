package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSulfurBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSulfurBehaviorImpl extends BlockBehaviorImpl implements BlockSulfurBehavior {
    public BlockSulfurBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
