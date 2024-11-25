package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAllowBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockAllowBehaviorImpl extends BlockBehaviorImpl implements BlockAllowBehavior {
    public BlockAllowBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
