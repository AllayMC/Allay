package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSmallAmethystBudBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSmallAmethystBudBehaviorImpl extends BlockBehaviorImpl implements BlockSmallAmethystBudBehavior {
    public BlockSmallAmethystBudBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
