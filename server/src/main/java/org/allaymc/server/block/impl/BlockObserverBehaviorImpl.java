package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockObserverBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockObserverBehaviorImpl extends BlockBehaviorImpl implements BlockObserverBehavior {
    public BlockObserverBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
