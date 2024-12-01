package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockEnderChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockEnderChestBehaviorImpl extends BlockBehaviorImpl implements BlockEnderChestBehavior {
    public BlockEnderChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
