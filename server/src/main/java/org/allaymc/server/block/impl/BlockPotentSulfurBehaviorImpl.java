package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockPotentSulfurBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

public class BlockPotentSulfurBehaviorImpl extends BlockBehaviorImpl implements BlockPotentSulfurBehavior {
    public BlockPotentSulfurBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
