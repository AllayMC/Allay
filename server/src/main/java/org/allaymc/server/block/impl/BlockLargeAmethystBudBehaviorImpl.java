package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLargeAmethystBudBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLargeAmethystBudBehaviorImpl extends BlockBehaviorImpl implements BlockLargeAmethystBudBehavior {
    public BlockLargeAmethystBudBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
