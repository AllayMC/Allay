package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockUnderwaterTorchBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockUnderwaterTorchBehaviorImpl extends BlockBehaviorImpl implements BlockUnderwaterTorchBehavior {
    public BlockUnderwaterTorchBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
