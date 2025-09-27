package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHoneycombBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockHoneycombBlockBehaviorImpl extends BlockBehaviorImpl implements BlockHoneycombBlockBehavior {
    public BlockHoneycombBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
