package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockSulfurBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

public class BlockSulfurBehaviorImpl extends BlockBehaviorImpl implements BlockSulfurBehavior {
    public BlockSulfurBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
