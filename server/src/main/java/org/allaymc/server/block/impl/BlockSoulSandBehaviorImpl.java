package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSoulSandBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSoulSandBehaviorImpl extends BlockBehaviorImpl implements BlockSoulSandBehavior {
    public BlockSoulSandBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
