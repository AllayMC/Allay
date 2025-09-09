package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockIronBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockIronBlockBehaviorImpl extends BlockBehaviorImpl implements BlockIronBlockBehavior {
    public BlockIronBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
