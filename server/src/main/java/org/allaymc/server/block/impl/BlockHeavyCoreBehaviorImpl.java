package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHeavyCoreBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockHeavyCoreBehaviorImpl extends BlockBehaviorImpl implements BlockHeavyCoreBehavior {
    public BlockHeavyCoreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
