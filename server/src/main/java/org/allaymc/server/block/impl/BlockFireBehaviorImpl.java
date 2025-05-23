package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFireBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFireBehaviorImpl extends BlockBehaviorImpl implements BlockFireBehavior {
    public BlockFireBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
