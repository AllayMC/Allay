package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSaplingBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSaplingBehaviorImpl extends BlockBehaviorImpl implements BlockSaplingBehavior {
    public BlockSaplingBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
