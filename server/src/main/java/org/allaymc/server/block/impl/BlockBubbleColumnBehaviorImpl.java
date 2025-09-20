package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBubbleColumnBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBubbleColumnBehaviorImpl extends BlockBehaviorImpl implements BlockBubbleColumnBehavior {
    public BlockBubbleColumnBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
