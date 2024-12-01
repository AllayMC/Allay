package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBellBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBellBehaviorImpl extends BlockBehaviorImpl implements BlockBellBehavior {
    public BlockBellBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
