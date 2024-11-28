package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockCopperTrapdoorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCopperTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockCopperTrapdoorBehavior {
    public BlockCopperTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
