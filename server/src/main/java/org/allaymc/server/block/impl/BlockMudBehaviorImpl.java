package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMudBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMudBehaviorImpl extends BlockBehaviorImpl implements BlockMudBehavior {
    public BlockMudBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
