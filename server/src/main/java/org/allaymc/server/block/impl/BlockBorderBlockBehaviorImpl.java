package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBorderBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBorderBlockBehaviorImpl extends BlockBehaviorImpl implements BlockBorderBlockBehavior {
    public BlockBorderBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
