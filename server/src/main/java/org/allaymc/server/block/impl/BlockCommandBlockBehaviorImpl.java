package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCommandBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCommandBlockBehaviorImpl extends BlockBehaviorImpl implements BlockCommandBlockBehavior {
    public BlockCommandBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
