package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSnowBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSnowBehaviorImpl extends BlockBehaviorImpl implements BlockSnowBehavior {
    public BlockSnowBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
