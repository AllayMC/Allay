package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockClientRequestPlaceholderBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockClientRequestPlaceholderBlockBehaviorImpl extends BlockBehaviorImpl implements BlockClientRequestPlaceholderBlockBehavior {
    public BlockClientRequestPlaceholderBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
