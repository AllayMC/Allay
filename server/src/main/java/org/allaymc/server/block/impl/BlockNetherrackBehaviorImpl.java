package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNetherrackBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockNetherrackBehaviorImpl extends BlockBehaviorImpl implements BlockNetherrackBehavior {
    public BlockNetherrackBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
