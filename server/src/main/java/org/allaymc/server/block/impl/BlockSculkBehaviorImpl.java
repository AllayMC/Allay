package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSculkBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSculkBehaviorImpl extends BlockBehaviorImpl implements BlockSculkBehavior {
    public BlockSculkBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
