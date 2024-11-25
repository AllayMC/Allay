package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockReedsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockReedsBehaviorImpl extends BlockBehaviorImpl implements BlockReedsBehavior {
    public BlockReedsBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
