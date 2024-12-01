package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTorchBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTorchBehaviorImpl extends BlockBehaviorImpl implements BlockTorchBehavior {
    public BlockTorchBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
