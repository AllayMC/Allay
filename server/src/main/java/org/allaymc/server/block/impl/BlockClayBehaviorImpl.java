package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockClayBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockClayBehaviorImpl extends BlockBehaviorImpl implements BlockClayBehavior {
    public BlockClayBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
