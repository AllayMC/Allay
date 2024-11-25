package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockObserverBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockObserverBehaviorImpl extends BlockBehaviorImpl implements BlockObserverBehavior {
    public BlockObserverBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
