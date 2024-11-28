package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSandBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSandBehaviorImpl extends BlockBehaviorImpl implements BlockSandBehavior {
    public BlockSandBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
