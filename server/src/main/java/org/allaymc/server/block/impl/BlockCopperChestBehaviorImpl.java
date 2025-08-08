package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockCopperChestBehavior {
    public BlockCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
