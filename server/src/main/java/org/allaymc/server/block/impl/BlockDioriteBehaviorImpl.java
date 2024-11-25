package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDioriteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDioriteBehaviorImpl extends BlockBehaviorImpl implements BlockDioriteBehavior {
    public BlockDioriteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
