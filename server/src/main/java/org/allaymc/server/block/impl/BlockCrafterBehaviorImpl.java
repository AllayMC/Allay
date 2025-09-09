package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCrafterBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCrafterBehaviorImpl extends BlockBehaviorImpl implements BlockCrafterBehavior {
    public BlockCrafterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
