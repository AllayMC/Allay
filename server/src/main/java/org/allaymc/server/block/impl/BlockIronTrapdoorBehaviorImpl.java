package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockIronTrapdoorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockIronTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockIronTrapdoorBehavior {
    public BlockIronTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
