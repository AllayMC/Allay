package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAnvilBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockAnvilBehaviorImpl extends BlockBehaviorImpl implements BlockAnvilBehavior {
    public BlockAnvilBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
