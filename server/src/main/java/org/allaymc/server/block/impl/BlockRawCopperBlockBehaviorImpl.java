package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRawCopperBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRawCopperBlockBehaviorImpl extends BlockBehaviorImpl implements BlockRawCopperBlockBehavior {
    public BlockRawCopperBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
