package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRailBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRailBehaviorImpl extends BlockBehaviorImpl implements BlockRailBehavior {
    public BlockRailBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
