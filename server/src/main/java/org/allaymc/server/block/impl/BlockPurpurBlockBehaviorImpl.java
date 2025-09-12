package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPurpurBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPurpurBlockBehaviorImpl extends BlockBehaviorImpl implements BlockPurpurBlockBehavior {
    public BlockPurpurBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
