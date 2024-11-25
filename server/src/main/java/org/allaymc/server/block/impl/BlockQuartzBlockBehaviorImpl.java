package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockQuartzBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockQuartzBlockBehaviorImpl extends BlockBehaviorImpl implements BlockQuartzBlockBehavior {
    public BlockQuartzBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
