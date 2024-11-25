package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockVineBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockVineBehaviorImpl extends BlockBehaviorImpl implements BlockVineBehavior {
    public BlockVineBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
