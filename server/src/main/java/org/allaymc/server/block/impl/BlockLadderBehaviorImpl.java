package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLadderBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLadderBehaviorImpl extends BlockBehaviorImpl implements BlockLadderBehavior {
    public BlockLadderBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
