package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAmethystBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockAmethystBlockBehaviorImpl extends BlockBehaviorImpl implements BlockAmethystBlockBehavior {
    public BlockAmethystBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
