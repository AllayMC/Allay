package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCornflowerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCornflowerBehaviorImpl extends BlockBehaviorImpl implements BlockCornflowerBehavior {
    public BlockCornflowerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
