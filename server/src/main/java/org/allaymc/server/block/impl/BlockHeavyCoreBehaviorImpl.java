package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHeavyCoreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockHeavyCoreBehaviorImpl extends BlockBehaviorImpl implements BlockHeavyCoreBehavior {
    public BlockHeavyCoreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
