package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStonecutterBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockStonecutterBehaviorImpl extends BlockBehaviorImpl implements BlockStonecutterBehavior {
    public BlockStonecutterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
