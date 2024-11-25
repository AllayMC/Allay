package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLodestoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLodestoneBehaviorImpl extends BlockBehaviorImpl implements BlockLodestoneBehavior {
    public BlockLodestoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
