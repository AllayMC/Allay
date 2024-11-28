package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockSandstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSandstoneBehaviorImpl extends BlockBehaviorImpl implements BlockSandstoneBehavior {
    public BlockSandstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
