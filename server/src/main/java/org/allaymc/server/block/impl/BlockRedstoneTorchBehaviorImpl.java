package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedstoneTorchBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRedstoneTorchBehaviorImpl extends BlockBehaviorImpl implements BlockRedstoneTorchBehavior {
    public BlockRedstoneTorchBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
