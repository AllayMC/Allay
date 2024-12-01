package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCopperBlockBehaviorImpl extends BlockBehaviorImpl implements BlockCopperBlockBehavior {
    public BlockCopperBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
