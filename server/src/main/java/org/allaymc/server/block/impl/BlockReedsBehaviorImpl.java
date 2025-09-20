package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockReedsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockReedsBehaviorImpl extends BlockBehaviorImpl implements BlockReedsBehavior {
    public BlockReedsBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
