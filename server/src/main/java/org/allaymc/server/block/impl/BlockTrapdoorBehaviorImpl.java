package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTrapdoorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockTrapdoorBehavior {
    public BlockTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
