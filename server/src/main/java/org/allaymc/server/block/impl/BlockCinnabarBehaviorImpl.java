package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCinnabarBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCinnabarBehaviorImpl extends BlockBehaviorImpl implements BlockCinnabarBehavior {
    public BlockCinnabarBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
