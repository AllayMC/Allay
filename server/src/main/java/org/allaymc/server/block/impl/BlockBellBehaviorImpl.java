package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBellBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBellBehaviorImpl extends BlockBehaviorImpl implements BlockBellBehavior {
    public BlockBellBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
