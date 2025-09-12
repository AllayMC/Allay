package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLodestoneBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLodestoneBehaviorImpl extends BlockBehaviorImpl implements BlockLodestoneBehavior {
    public BlockLodestoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
