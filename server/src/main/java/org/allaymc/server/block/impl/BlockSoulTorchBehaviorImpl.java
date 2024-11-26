package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSoulTorchBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSoulTorchBehaviorImpl extends BlockBehaviorImpl implements BlockSoulTorchBehavior {
    public BlockSoulTorchBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
