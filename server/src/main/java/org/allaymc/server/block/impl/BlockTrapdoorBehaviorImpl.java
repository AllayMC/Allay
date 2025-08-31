package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTrapdoorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockTrapdoorBehavior {
    public BlockTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
