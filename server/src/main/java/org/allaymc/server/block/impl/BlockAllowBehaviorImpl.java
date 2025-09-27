package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAllowBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockAllowBehaviorImpl extends BlockBehaviorImpl implements BlockAllowBehavior {
    public BlockAllowBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
