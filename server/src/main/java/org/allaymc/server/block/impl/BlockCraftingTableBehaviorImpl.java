package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCraftingTableBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCraftingTableBehaviorImpl extends BlockBehaviorImpl implements BlockCraftingTableBehavior {
    public BlockCraftingTableBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
