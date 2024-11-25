package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStonecutterBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockStonecutterBehaviorImpl extends BlockBehaviorImpl implements BlockStonecutterBehavior {
    public BlockStonecutterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
