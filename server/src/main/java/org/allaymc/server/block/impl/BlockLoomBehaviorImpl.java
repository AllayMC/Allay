package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLoomBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLoomBehaviorImpl extends BlockBehaviorImpl implements BlockLoomBehavior {
    public BlockLoomBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
