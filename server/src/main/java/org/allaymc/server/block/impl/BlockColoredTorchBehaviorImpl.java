package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockColoredTorchBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockColoredTorchBehaviorImpl extends BlockBehaviorImpl implements BlockColoredTorchBehavior {
    public BlockColoredTorchBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
