package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperTrapdoorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCopperTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockCopperTrapdoorBehavior {
    public BlockCopperTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
