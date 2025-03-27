package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBushBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBushBehaviorImpl extends BlockBehaviorImpl implements BlockBushBehavior {
    public BlockBushBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
