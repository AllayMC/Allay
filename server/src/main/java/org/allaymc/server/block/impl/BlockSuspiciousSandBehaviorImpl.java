package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSuspiciousSandBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSuspiciousSandBehaviorImpl extends BlockBehaviorImpl implements BlockSuspiciousSandBehavior {
    public BlockSuspiciousSandBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
