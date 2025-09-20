package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMangrovePropaguleBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockMangrovePropaguleBehaviorImpl extends BlockBehaviorImpl implements BlockMangrovePropaguleBehavior {
    public BlockMangrovePropaguleBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
